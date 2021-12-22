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
			System.out.println("클라이언트 연결 대기중....");
			Socket sk = ssk.accept();
			System.out.println(sk.getInetAddress()+"님이 접속 하였습니다.");
			
			//접속한 클라이언트와 통신을 담당하는 스레드 생성
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
		}// ClientHandler 생성자
		
		
		public void run(){
			String nickName = null;
			try {
				nickName = di.readUTF(); //로그인 클라이언트의 닉네임을 얻어오기
				output(nickName+"님이 입장하셨습니다...");
				while(!flag){
					String Cmsg = di.readUTF(); // 클라이언트의 대화내용을 읽어오기
					output(nickName+" > "+Cmsg);
				}
				
			} catch (IOException e) {
				output(nickName+"님이 나가셨습니다...");
				e.printStackTrace();				
			}
		}//run()
		
		
		//모든 클라이언트에게 메세지를 출력해주는 기능 
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
