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
			System.out.println("���� �����~~~");
			sk = ssk.accept();
			System.out.println(sk.getLocalPort()+"��Ʈ�� Ŭ���̾�Ʈ "+sk.getInetAddress()+"�� ������ �Ǿ����ϴ�.");
			
			// Ŭ���̾�Ʈ�� ����ϱ����� ��Ʈ���� ����
			
			InputStream ins = sk.getInputStream();
			InputStreamReader inR = new InputStreamReader(ins);
			
			BufferedReader bffR = new BufferedReader(inR);
			
			OutputStream out = sk.getOutputStream();
			PrintWriter pout = new PrintWriter(out, true);
			
			pout.println("����!!");			
			
			String clientMsg = "";
			while((clientMsg = bffR.readLine()) !=null){
				if(clientMsg.startsWith("�ȳ�")|| clientMsg.startsWith("����")){
					pout.println(sk.getInetAddress()+"�� �ݰ����ϴ�..^^");
				}else if(clientMsg.startsWith("������ ����")){
					Date today = new Date();
					pout.println(today.toString());
				}else{
					pout.println(sk.getInetAddress()+"�� bye bye~~");
				}
				
			}
			
			
			inR.close(); bffR.close(); out.close();sk.close();ssk.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
