import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class DemoClient {

	public static void main(String[] args) throws Exception {
		
		//연결시에 소켓이 생성이된다. 연결이 안될경우에는 예외발생한다.
		Socket sk = new Socket("192.168.25.37",5050);
		System.out.println("서버와 접속이 되었습니다....");
		
		InputStream ins = sk.getInputStream();
		
		DataInputStream dins = new DataInputStream(ins);
		
		String str = dins.readUTF();
		int number = dins.readInt();
		System.out.println("서버에서 전송된 문자 :"+str);
		System.out.println("서버에서 전송된 값 :"+number);
		
		dins.close(); ins.close(); sk.close();		
				
	}

}
