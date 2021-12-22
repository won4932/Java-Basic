package recursion;
import java.util.Arrays;
import java.util.Scanner;
// Àç±Í
public class Back11729 {
	static int count=0;
	 static StringBuilder result = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
       
        move(1,2,3,N);
        System.out.println(count);
        System.out.println(result);
    }
    public static void move(int one, int two, int three, int N) {
    	if(N==1) {
    		count++;
    		result.append(one + " " + three + "\n");
    	}else {
    		move(one,three,two,N-1);
    		count++;
    		result.append(one + " " + three + "\n");
    		move(two, one, three, N-1);
    	}
    	
    }
}