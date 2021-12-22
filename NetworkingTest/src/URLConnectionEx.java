import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class URLConnectionEx {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String str ="http://shopping.phinf.naver.net/20150206_18/3699210f-118b-46fc-8932-7dc627855fe1.jpg";
		
		URL url = new URL(str);
		
		URLConnection conn = url.openConnection();
		
		System.out.println("toString() :"+conn);
		int size = conn.getContentLength();
		System.out.println("���� ũ��: "+size);
		System.out.println("���� Ÿ��: "+conn.getContentType());
		System.out.println("���� ��¥: "+conn.getDate());

	}

}
