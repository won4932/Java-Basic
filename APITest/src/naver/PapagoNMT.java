package naver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLDecoder;

public class PapagoNMT {

	public static String KOREAN = "ko";
	public static String ENGLISH = "en";
	public static String FRENCH = "fr";
	public static String CHINESE = "zh-CN";
	public static String VIETNAMESE = "vi";

	// ���İ� API ���� �ּ�
    private static String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	// ���ø����̼� Ŭ���̾�Ʈ ���̵� ��
    private static String clientId = "";
    // ���ø����̼� Ŭ���̾�Ʈ ��ũ�� ��
    private static String clientSecret = "";
    
    // ���ʴ�� '��ȯ�� ���', '��ȯ�� ���', '��ȯ�� ����'
    public static String translate(String source, String target, String text) {
    	try {
        	// ��ȯ�� ������ UTF-8 �����ڵ�� ���ڵ��մϴ�.
        	text = URLEncoder.encode(text, "UTF-8");
        	// ���İ� API ������ �����մϴ�.
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // ���İ� API ������ ������ �Ķ���͸� �����մϴ�.
            String postParams = "source=" + source + "&target=" + target + "&text=" + text;
            // ���İ� API ������ ������ ������ �����մϴ�.
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            // ���İ� API �����κ��� ������ �޽����� ���� �޽��ϴ�.
            int responseCode = con.getResponseCode();
            BufferedReader br;
            // ������ ������ ���
            if(responseCode == 200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            // ������ �߻��� ���
            else {  
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            // ���޹��� �޽����� ����մϴ�.
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response.toString();
    	} catch (Exception e) {
    		return "���İ� API ��� �߿� ������ �߻��߽��ϴ�.";
    	}
    }
}
