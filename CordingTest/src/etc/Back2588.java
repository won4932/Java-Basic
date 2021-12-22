package etc;
import java.util.Scanner;
 //입출력 및 연습

public class Back2588 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	int a = sc.nextInt();
	sc.nextLine();
	String b = sc.next();
	String[] c;
	c = b.split("");
	int Sum = 0;
	for (int i = c.length-1; i >= 0; i--) {
		int Total = a*(Integer.parseInt(c[i]));
		
		System.out.println(Total);
		if(i==1) Total*=10;
		if(i==0) Total*=100;
		Sum +=Total;
		}
	System.out.println(Sum);
	}
}