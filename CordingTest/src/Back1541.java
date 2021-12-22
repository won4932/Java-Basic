import java.util.Scanner;

public class Back1541 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] N = sc.nextLine().split("-");
		int result = 0;
		for(int i = 0; i<N.length; i++) {
			int temp = plus(N[i]);
			if(i == 0) result += temp;
			else result -= temp;
		}
		System.out.println(result);
		
	}
	public static int plus(String s) {
		String[] result = s.split("\\+");
		int temp = 0;
		for(int i = 0; i<result.length; i++) {
			temp += Integer.parseInt(result[i]); 
		}
		
		return temp;
	}

}
