import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class URLFileCopyEx {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String str ="http://img.naver.net/static/www/u/2013/0731/nmms_224940510.gif";
		
		URL url = new URL(str);
		
		InputStream in = url.openStream();
		
		BufferedInputStream bi = new BufferedInputStream(in);
		
		FileOutputStream fo = new FileOutputStream("img.jpg");
		
		byte buff[] = new byte[1024];
		int imgData =0;
		int cnt = 0;
		URLConnection conn = url.openConnection();
		int size = conn.getContentLength();
		System.out.println("이미지크기:" +size);
		
		while((imgData = bi.read(buff)) !=-1){
			fo.write(buff, 0, imgData);
			fo.flush();
			cnt +=imgData;
			System.out.println(((cnt*100)/size)+"%");
		}
		in.close(); bi.close(); fo.close();

	}

}
