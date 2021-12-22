package etc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//¹®ÀÚ¿­
public class Back2941 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String alpet;
        String[] crotia = new String[] {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        alpet = br.readLine();
        for (int i = 0; i < crotia.length; i++) {
			alpet = alpet.replace(crotia[i], "*");
		}
        System.out.println(alpet.length());
    }
    
}