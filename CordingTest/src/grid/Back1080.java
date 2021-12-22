package grid;
import java.util.Scanner;

public class Back1080 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int before[][] = new int[N][M];
		int after[][] = new int[N][M];
		int count =0;
		String temp = "";
		for(int i = 0; i<N; i++) {
			temp = sc.next();
			for(int j = 0; j<M; j++) {
				before[i][j] = temp.charAt(j)-'0';
			}
		}
		for(int i = 0; i<N; i++) {
			temp = sc.next();
			for(int j = 0; j<M; j++) {
				after[i][j] = temp.charAt(j)-'0';
			}
		}
		
		for(int k = 0; k<=N-3; k++) {
			for(int q = 0; q<=M-3; q++) {
				if(before[k][q]!=after[k][q]) {
					count++;
					for(int i =k; i<k+3; i++) {
						for(int j = q; j<q+3; j++) {
							if(before[i][j] == 1) before[i][j] = 0;
							else before[i][j] = 1;
						}
					}
				}
			}
		}
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(before[i][j]!=after[i][j]) {
					count = -1;
				}
			}
		}
		System.out.println(count);
		
	}

}
