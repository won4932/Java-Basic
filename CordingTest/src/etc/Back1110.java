package etc;
import java.util.Scanner;
//while¹®
public class Back1110 {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count=0;
        String N = sc.next();
        String X="";
        String[] temp = new String[2];
        String[] temp2;
        if(Integer.parseInt(N)<10) {
        	temp[0] = "0";
        	temp[1] = N;
        	N= "0"+N;
        }else
        	temp = N.split("");
        
        while(!N.equals(X)) {
		int t = Integer.parseInt(temp[0]) + Integer.parseInt(temp[1]);
		if(Integer.toString(t).length() == 2) {
			temp2 = Integer.toString(t).split("");
			X = temp[1] + temp2[1];
		}else X = temp[1] + Integer.toString(t);
		temp = X.split("");
		count++;
        }
        
		System.out.println(count);
    }
}