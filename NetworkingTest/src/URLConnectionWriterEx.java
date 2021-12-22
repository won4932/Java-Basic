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
		// URL ����
		URL url = new URL("http://httpbin.org/post");
		
		//URLConnection ��ü�� ����
		URLConnection uc = url.openConnection();
		
		uc.setDoOutput(true); //doOutput �ʵ尪�� true����
		
		OutputStreamWriter ow = new OutputStreamWriter(uc.getOutputStream()); //��� ��Ʈ������
		
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
