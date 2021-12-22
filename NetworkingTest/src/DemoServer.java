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
		//���� ������ ����
		ServerSocket ssk = new ServerSocket(port);
		
		System.out.println("���� �����~");
		
		while(true){
			Socket sock = ssk.accept();
			System.out.println("����� �����߽��ϴ�..");
			System.out.println("Ŭ���̾�Ʈ ip :"+sock.getInetAddress().getHostAddress());
			
			//Ŭ���̾�Ʈ�� ������ ���� ��Ʈ���� ����
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
