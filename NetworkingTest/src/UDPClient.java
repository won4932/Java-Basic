import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UDPClient {
	
	public static final int port = 6060;
	public static void main(String[] args) throws Exception {
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		
		BufferedReader buR = null;
		InputStreamReader inR = new InputStreamReader(System.in);
		
		buR = new BufferedReader(inR);
		
		String sendMsg = "";
		System.out.println("전송할 내용을 입력하세요...");
		DatagramSocket ds = new DatagramSocket();
		DatagramPacket dp = null;
		
		while((sendMsg = buR.readLine())!= null){
			if(sendMsg.equalsIgnoreCase("x")) break;
			byte[] n = sendMsg.getBytes();
			dp = new DatagramPacket(n,n.length,ip,port);
			ds.send(dp);
			
		}//		

	}

}
