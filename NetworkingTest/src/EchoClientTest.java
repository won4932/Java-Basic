import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class EchoClientTest {

	public static void main(String[] args) throws Exception {
		String ip = "127.0.0.1";
		int port = 7070;
		
		Socket sk = new Socket(ip, port);
		System.out.println("������ ������ �Ǿ����ϴ�.!!!");
		
		// ������ ���� �����͸� �Է¹޴� ��Ʈ��
		InputStream ins = sk.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader bff = new BufferedReader(isr);
		
		OutputStream ous =sk.getOutputStream();
		PrintWriter pout = new PrintWriter(ous, true);
		
		//Ű����� ���� �����͸� �Է¹޴� ��Ʈ��
		InputStreamReader inR = new InputStreamReader(System.in);
		BufferedReader bffKey = new BufferedReader(inR);
		
		
		String serverMsg = "", sendMsg="";
		
		serverMsg = bff.readLine();
		System.out.println("�����޼���> "+serverMsg);
		
		while((sendMsg=bffKey.readLine()) !=null){
			pout.println(sendMsg);
			serverMsg = bff.readLine();
			System.out.println("�����޼���> "+serverMsg);
		}
		
		inR.close(); bffKey.close(); bff.close(); sk.close();
	}

}
