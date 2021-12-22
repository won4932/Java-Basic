package DP;

import java.util.Scanner;
/*
 *  1로 만들기
 	X가 3으로 나누어 떨어지면, 3으로 나눈다.
 	X가 2로 나누어 떨어지면, 2로 나눈다.
 	1을 뺀다.
 	연산을 하는 횟수의 최솟값
 */
 
// Top-down
public class Back1463 {
    static int[] d;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        d = new int[n + 1]; // 최대연산은 -1씩 계속 뺴는 것이므로 배열의 최대값은 N+1
 
        System.out.println(dp(n));
        sc.close();
    }
 
    private static int dp(int n) {
        if (n == 1)
            return 0;
        if (d[n] > 0)
            return d[n];
        d[n] = dp(n - 1) + 1; // d[n]의 최악의 수는 d[n-1]을 최소값의 +1한것 
        					  // n=4, 최솟값이 3라면 2,3으로 안나눠지면 최악의경우 -1을 해야되기떄문에
        if (n % 2 == 0) {
            int tmp = dp(n / 2) + 1;
            if (d[n] > tmp)
                d[n] = tmp;
        }
        if (n % 3 == 0) {
            int tmp = dp(n / 3) + 1;
            if (d[n] > tmp)
                d[n] = tmp;
        }
        return d[n];
    }
    /* Bottom Up */
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//            int n = sc.nextInt();
//            int dp[] = new int[n + 1];
//            dp[0] = dp[1] = 0;
//            for (int i = 2; i <= n; i++) {
//                dp[i] = dp[i - 1] + 1;
//                if (i % 2 == 0)
//                    dp[i] = Math.min(dp[i], dp[i / 2] + 1);
//                if (i % 3 == 0)
//                    dp[i] = Math.min(dp[i], dp[i / 3] + 1);
//            }
//            System.out.println(dp[n]);
//            sc.close();
//        }
     

}
 
