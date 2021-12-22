package DP;

import java.util.Scanner;

/*
 * 1,2,3더하기
 * 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램
 */
public class Back9095 {
	static int[] dp;
	
	// Bottom-up
//	public static void main(String[] args) {
//		int[] dp = new int[12];
//		dp[1] = 1;
//		dp[2] = 2;
//		dp[3] = 4;
//		Scanner sc = new Scanner(System.in);
//		int T = sc.nextInt();
//		sc.nextLine();
//		for (int i = 0; i < T; i++) {
//			int N = sc.nextInt();			
//		
//		for(int j =4; j<=N; j++) {
//				dp[j] = dp[j-1]+ dp[j-2] + dp[j-3];
//		}
//		System.out.println(dp[N]);
//	}
//	}
	// Top-down
	public static void main(String[] args) {
		dp = new int[12];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			System.out.println(dp(N));
	}
	}
	private static int dp(int n) {
		if(n==1) return 1;
		if(n==2) return 2;
		if(n==3) return 4;
		dp[n] = dp(n-1) + dp(n-2) + dp(n-3); 
		return dp[n];
	}
}
