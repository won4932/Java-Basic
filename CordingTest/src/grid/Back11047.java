package grid;
import java.util.Scanner;

public class Back11047 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int j = N-1;
		int count=0;
		int[] coin = new int[N];
		for(int i=0; i<N; i++) {
			coin[i] = sc.nextInt();
		}
		while(K != 0) {
			int ch = K / coin[j];
			K -= ch * coin[j--];
			count += ch;
		}
		System.out.println(count);
	}

}
