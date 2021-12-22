import java.net.InetAddress;
import java.net.UnknownHostException;


public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress ia = null;
		InetAddress[] iaArr = null;
		
		ia = InetAddress.getByName("www.naver.com");
		System.out.println("ȣ��Ʈ ����:"+ia.getHostName());
		System.out.println("ȣ��Ʈ�� ip�ּ�:"+ia.getHostAddress());
		System.out.println(ia.toString()); //�����θ�� ip�ּҸ� ������ ����
		
		
		iaArr = InetAddress.getAllByName("www.naver.com");
		
		for(int i = 0; i<iaArr.length; i++){
			System.out.println(iaArr[i]);
		}
		
		
		ia = InetAddress.getLocalHost();
		System.out.println("����ȣ��Ʈ :"+ia.getHostName());
		System.out.println("���� ȣ��Ʈ �ּ�:"+ia.getHostAddress());
		

	}

}
