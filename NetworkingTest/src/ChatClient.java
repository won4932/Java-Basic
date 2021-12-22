import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.*;
import java.awt.*;


public class ChatClient extends JApplet implements ActionListener, Runnable{
	int port =9090; 
	DataInputStream di;
	DataOutputStream dou;
	JTextField tfNickName, tfInput;
	JTextArea ta;
	CardLayout card;
	Thread th;
	boolean flag = false;
	Container cont;
	
	int lineCnt;
	String lastMsg;
	
	Socket sk;
	
	public void init(){
		cont = getContentPane();
		card = new CardLayout();
		cont.setLayout(card);
		
		JPanel nameP = new JPanel();
		JPanel chatP = new JPanel(new BorderLayout());
		
		cont.add(nameP,"login");
		cont.add(chatP,"chat");
		card.show(cont, "login");
		
		nameP.add(new JLabel("�г���"));
		nameP.add(tfNickName=new JTextField(10));
		
		chatP.add(new JScrollPane(ta=new JTextArea()));
		chatP.add(tfInput=new JTextField(),"South");
		ta.setEditable(false);
		ta.setBackground(Color.ORANGE);
		ta.setForeground(Color.black);
		
		//������ ���
		tfNickName.addActionListener(this);
		tfInput.addActionListener(this);
	}//init()
	
	public void start(){
		// �����带 ���۽�Ų��.
		th = new Thread(this);
		th.start();
	}
	
	public void stop(){
		// ������ ����
		if(th != null){
			flag = true;
		}
		th = null;
	}
	
	public void destroy(){
		
			try {
				if(di !=null)di.close();
				if(dou !=null) dou.close();
				if(sk !=null) sk.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	
	public void actionPerformed(ActionEvent ae){
		Object obj=ae.getSource();
		if(obj == tfNickName){
			String nickName = tfNickName.getText();
			if(nickName ==null ||nickName.trim().equals("")){
				showStatus("�г����� �Է��ϼ���....");
				return;
			}
			try {
				dou.writeUTF(nickName);
				dou.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ä�� ȭ�� �����ֱ�
			card.show(cont, "chat");
			
			tfInput.setEditable(true);
			tfInput.requestFocus();			
		}else if(obj == tfInput){
			String sendMsg = tfInput.getText();
			
			try {
				dou.writeUTF(sendMsg);
				dou.flush();
				tfInput.setText("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}//actionPerformed()
	
	
	public void run(){
		// ������ �����ϱ����� ���� �ּ� ������
		String ip = getCodeBase().getHost();
		System.out.println(ip);
		ta.append(ip+"������ ������...\n ");
		
		try {
			sk = new Socket(ip,port);
			ta.append("����Ǿ����ϴ�.!!\n");
			InputStream in = sk.getInputStream();
			OutputStream out = sk.getOutputStream();
			
			di = new DataInputStream(in);
			dou = new DataOutputStream(out);
			
			readfunc();
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}//
	
	//�������� �޽����� �о���� �޼���
	public void readfunc() throws Exception{
		String serverMsg = "";
		while(!flag){
			serverMsg = di.readUTF();
			if(lineCnt >20){
				ta.setText("");
				ta.append(serverMsg+"\n");
				lineCnt = 0;
			}
			
			ta.append(serverMsg+"\n");
			lastMsg = serverMsg;
			lineCnt++;
			
		}
	}
	
	

}
