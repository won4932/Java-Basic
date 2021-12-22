package etc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//¹®ÀÚ¿­
public class Back5622 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String alpet;
        int result=0;
        int temp;
        alpet = br.readLine();
        for (int i = 0; i < alpet.length(); i++) {
        	if(alpet.charAt(i)=='Z') result +=10;
        	else {
        	temp = alpet.charAt(i);
        	if(temp>82) temp = (temp-57)/3;
        	else temp = (temp-56)/3;

			result +=temp;
        	}
		}
        System.out.println(result);
    }
    
}