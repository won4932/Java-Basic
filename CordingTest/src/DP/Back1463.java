package DP;

import java.util.Scanner;
/*
 *  1�� �����
 	X�� 3���� ������ ��������, 3���� ������.
 	X�� 2�� ������ ��������, 2�� ������.
 	1�� ����.
 	������ �ϴ� Ƚ���� �ּڰ�
 */
 
// Top-down
public class Back1463 {
    static int[] d;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        d = new int[n + 1]; // �ִ뿬���� -1�� ��� ���� ���̹Ƿ� �迭�� �ִ밪�� N+1
 
        System.out.println(dp(n));
        sc.close();
    }
 
    private static int dp(int n) {
        if (n == 1)
            return 0;
        if (d[n] > 0)
            return d[n];
        d[n] = dp(n - 1) + 1; // d[n]�� �־��� ���� d[n-1]�� �ּҰ��� +1�Ѱ� 
        					  // n=4, �ּڰ��� 3��� 2,3���� �ȳ������� �־��ǰ�� -1�� �ؾߵǱ⋚����
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
 
