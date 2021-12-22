import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class URLConnectionWriterEx {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// URL 생성
		URL url = new URL("http://httpbin.org/post");
		
		//URLConnection 객체를 생성
		URLConnection uc = url.openConnection();
		
		uc.setDoOutput(true); //doOutput 필드값을 true설정
		
		OutputStreamWriter ow = new OutputStreamWriter(uc.getOutputStream()); //출력 스트림생성
		
		ow.write("Name=Hong Gil Dong&Number=34");
		ow.close();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		
		String str ;
		
		while((str = in.readLine()) != null){
			System.out.println(str);			
		}
		in.close();

		

	}

}
