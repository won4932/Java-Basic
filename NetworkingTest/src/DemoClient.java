import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class DemoClient {

	public static void main(String[] args) throws Exception {
		
		//����ÿ� ������ �����̵ȴ�. ������ �ȵɰ�쿡�� ���ܹ߻��Ѵ�.
		Socket sk = new Socket("192.168.25.37",5050);
		System.out.println("������ ������ �Ǿ����ϴ�....");
		
		InputStream ins = sk.getInputStream();
		
		DataInputStream dins = new DataInputStream(ins);
		
		String str = dins.readUTF();
		int number = dins.readInt();
		System.out.println("�������� ���۵� ���� :"+str);
		System.out.println("�������� ���۵� �� :"+number);
		
		dins.close(); ins.close(); sk.close();		
				
	}

}
