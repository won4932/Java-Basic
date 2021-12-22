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
		
		nameP.add(new JLabel("닉네임"));
		nameP.add(tfNickName=new JTextField(10));
		
		chatP.add(new JScrollPane(ta=new JTextArea()));
		chatP.add(tfInput=new JTextField(),"South");
		ta.setEditable(false);
		ta.setBackground(Color.ORANGE);
		ta.setForeground(Color.black);
		
		//리스너 등록
		tfNickName.addActionListener(this);
		tfInput.addActionListener(this);
	}//init()
	
	public void start(){
		// 스레드를 동작시킨다.
		th = new Thread(this);
		th.start();
	}
	
	public void stop(){
		// 스레드 정지
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
				showStatus("닉네임을 입력하세요....");
				return;
			}
			try {
				dou.writeUTF(nickName);
				dou.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//채팅 화면 보여주기
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
		// 서버에 접속하기위한 서버 주소 얻어오기
		String ip = getCodeBase().getHost();
		System.out.println(ip);
		ta.append(ip+"서버와 연결중...\n ");
		
		try {
			sk = new Socket(ip,port);
			ta.append("연결되었습니다.!!\n");
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
	
	//서버에서 메시지를 읽어오는 메서드
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
