import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class UDPServer {

	public static void main(String[] args) throws Exception {
		byte[] buffer = new byte[256];
		
		DatagramSocket ds = new DatagramSocket(6060);
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		
		while(true){
			System.out.println("연결대기중~~~");
			ds.receive(dp);
			byte bufferMsg[] = dp.getData();
			String clientMsg = new String(bufferMsg,0,dp.getLength());
			System.out.println(dp.getAddress()+">>"+clientMsg);
		}		
	}

}
