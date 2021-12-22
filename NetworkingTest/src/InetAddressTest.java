import java.net.InetAddress;
import java.net.UnknownHostException;


public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress ia = null;
		InetAddress[] iaArr = null;
		
		ia = InetAddress.getByName("www.naver.com");
		System.out.println("호스트 네임:"+ia.getHostName());
		System.out.println("호스트의 ip주소:"+ia.getHostAddress());
		System.out.println(ia.toString()); //도메인명과 ip주소를 얻어오는 역할
		
		
		iaArr = InetAddress.getAllByName("www.naver.com");
		
		for(int i = 0; i<iaArr.length; i++){
			System.out.println(iaArr[i]);
		}
		
		
		ia = InetAddress.getLocalHost();
		System.out.println("로컬호스트 :"+ia.getHostName());
		System.out.println("로컬 호스트 주소:"+ia.getHostAddress());
		

	}

}
