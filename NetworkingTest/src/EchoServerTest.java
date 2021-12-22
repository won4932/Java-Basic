import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class EchoServerTest {

	public static void main(String[] args) {
		
		int port = 7070;
		ServerSocket  ssk = null;
		Socket sk = null;
		
		try {
			ssk = new ServerSocket(port);
			System.out.println("접속 대기중~~~");
			sk = ssk.accept();
			System.out.println(sk.getLocalPort()+"포트와 클라이언트 "+sk.getInetAddress()+"과 접속이 되었습니다.");
			
			// 클라이언트와 통신하기위한 스트림을 생성
			
			InputStream ins = sk.getInputStream();
			InputStreamReader inR = new InputStreamReader(ins);
			
			BufferedReader bffR = new BufferedReader(inR);
			
			OutputStream out = sk.getOutputStream();
			PrintWriter pout = new PrintWriter(out, true);
			
			pout.println("하이!!");			
			
			String clientMsg = "";
			while((clientMsg = bffR.readLine()) !=null){
				if(clientMsg.startsWith("안녕")|| clientMsg.startsWith("하이")){
					pout.println(sk.getInetAddress()+"님 반갑습니다..^^");
				}else if(clientMsg.startsWith("오늘은 몇일")){
					Date today = new Date();
					pout.println(today.toString());
				}else{
					pout.println(sk.getInetAddress()+"님 bye bye~~");
				}
				
			}
			
			
			inR.close(); bffR.close(); out.close();sk.close();ssk.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
