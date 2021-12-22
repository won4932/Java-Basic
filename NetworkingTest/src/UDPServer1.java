import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UDPServer1 {
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(6060);
		
		while(true){
			System.out.println("클라이언트 연결 대기중~~~");
			byte buffer[] = new byte[100];
			
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			socket.receive(dp); // 클라이언트의 정보가 패킷 안에 들어있다
			
			String woonse = TodayWoonse.selWoonse(); //운세를 얻어온다.
			buffer = woonse.getBytes();
			
			InetAddress ip = dp.getAddress();
			int port = dp.getPort();
			System.out.println("["+port+"] 가 접속함");
			
			//운세를 클라이언트에게 전송
			dp = new DatagramPacket(buffer, buffer.length, ip, port);
			socket.send(dp);
		}

	}

}
