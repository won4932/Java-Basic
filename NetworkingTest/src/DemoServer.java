import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class DemoServer {

	public static void main(String[] args) throws IOException {
		int port = 5050;
		
		int number = Integer.parseInt(args[0]);
		String str = new String(args[1]);		
		//서버 소켓을 생성
		ServerSocket ssk = new ServerSocket(port);
		
		System.out.println("접속 대기중~");
		
		while(true){
			Socket sock = ssk.accept();
			System.out.println("사용자 접속했습니다..");
			System.out.println("클라이어트 ip :"+sock.getInetAddress().getHostAddress());
			
			//클라이언트와 연결을 위한 스트림을 생성
			OutputStream ous = sock.getOutputStream();
			DataOutputStream dous = new DataOutputStream(ous);
			
			dous.writeUTF(str);
			dous.writeInt(number);
			
			dous.flush();
			dous.close();
			sock.close();
			
		}//while
		
	}

}
