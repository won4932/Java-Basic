import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class URLEx1 {

	public static void main(String[] args) throws Exception {
		URL url = new URL(args[0]);
		
		System.out.println("�������� : "+url.getProtocol());
		System.out.println("ȣ��Ʈ : "+url.getHost());
		System.out.println("��Ʈ : "+ url.getPort());
		System.out.println("���� : "+ url.getFile());
		
		
		InputStream ins = url.openStream();
		
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(ins));
		
		String str="";
		while((str = br.readLine()) !=null){
			System.out.println(str);
		}
		br.close(); ins.close();
		
	}
	
	

}
