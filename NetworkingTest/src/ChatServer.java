import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;


public class ChatServer {
	
	Vector<ClientHandler> clThrs;
	
	public ChatServer (int port) throws Exception{
		ServerSocket ssk = new ServerSocket(port);
		clThrs = new Vector<ClientHandler>(4,2);
		while(true){
			System.out.println("Ŭ���̾�Ʈ ���� �����....");
			Socket sk = ssk.accept();
			System.out.println(sk.getInetAddress()+"���� ���� �Ͽ����ϴ�.");
			
			//������ Ŭ���̾�Ʈ�� ����� ����ϴ� ������ ����
			ClientHandler clThr = new ClientHandler(this, sk);
			
			clThrs.add(clThr);
			clThr.start();		
			
			
		}// while
	}	
	
	
	public static void main(String[] args) {
		try {
			new ChatServer(9090);
		} catch (Exception e) {			
			e.printStackTrace();
		}	

	}
	
	class ClientHandler extends Thread{
		ChatServer ssk;
		Socket sk;
		
		DataInputStream di;
		DataOutputStream dou;
		boolean flag = false;
		
		public ClientHandler(ChatServer ssk, Socket sk){
			this.ssk = ssk;
			this.sk=sk;
			
			try {
				di = new DataInputStream(sk.getInputStream());
				dou = new DataOutputStream(sk.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// ClientHandler ������
		
		
		public void run(){
			String nickName = null;
			try {
				nickName = di.readUTF(); //�α��� Ŭ���̾�Ʈ�� �г����� ������
				output(nickName+"���� �����ϼ̽��ϴ�...");
				while(!flag){
					String Cmsg = di.readUTF(); // Ŭ���̾�Ʈ�� ��ȭ������ �о����
					output(nickName+" > "+Cmsg);
				}
				
			} catch (IOException e) {
				output(nickName+"���� �����̽��ϴ�...");
				e.printStackTrace();				
			}
		}//run()
		
		
		//��� Ŭ���̾�Ʈ���� �޼����� ������ִ� ��� 
		synchronized public void output(String msg){
			// Enumeration<ClientHandler> enu = ssk.clThrs.elements();
			Iterator it = ssk.clThrs.iterator();
			
			while(it.hasNext()){
				ClientHandler cTh = (ClientHandler)it.next();
				try {
					cTh.dou.writeUTF(msg);
					cTh.dou.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
//			while(enu.hasMoreElements()){
//				ClientHandler cTh = enu.nextElement();
//				
//				try {
//					cTh.dou.writeUTF(msg);
//					cTh.dou.flush();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}//while
			
			
		}//output()
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
