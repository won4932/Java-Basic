import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ChattingClient implements Runnable {
	String ip = "192.168.25.48";
	int port =6060;
	Socket sk; 
	
	public ChattingClient() {
		
			try {
			sk = new Socket(ip, port);	
			System.out.println("채팅서버와 연결되었습니다.....");
			// 서버로 보낼 메세지를 키보드로 입력받는 스트림 생성
			InputStreamReader inR = new InputStreamReader(System.in);
			BufferedReader buR = new BufferedReader(inR);
			
			PrintWriter pout = new PrintWriter(sk.getOutputStream(), true);
			
			Thread thr = new Thread(this);
			thr.start();
			String sendMsg = "";
			while((sendMsg = buR.readLine())!=null){
				pout.println(sendMsg);
			}//while
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}//생성자
	
	public void run(){
		try {
			InputStream ins = sk.getInputStream();
			BufferedReader buR = new BufferedReader(new InputStreamReader(ins));
			String svrMsg = "";
			while(true){
				svrMsg = buR.readLine();
				System.out.println("Server > "+svrMsg);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {	
		new ChattingClient();
		
	}

}
