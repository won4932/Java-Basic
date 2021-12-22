package DP;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Back2579 {
	static int dp[];
	static int score[];
	// Top-down
    public static void main(String[] args) {
//    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	try {
//			int Case = Integer.parseInt(br.readLine());
//			dp = new int[Case +1];
//			score = new int[Case + 1];
//			for(int i =0; i<Case; i++) {
//				score[i] = Integer.parseInt(br.readLine());
//			}
//			System.out.println(stair(Case));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
			int Case = Integer.parseInt(br.readLine());
			dp = new int[Case +1];
			score = new int[Case + 1];
			for(int i =1; i<=Case; i++) {
				score[i] = Integer.parseInt(br.readLine());
			}
			dp[1] = score[1];
			dp[2] = dp[1] + score[2];
			for (int i = 3; i <= Case; i++) {
				// ù�� �����ΰ���, �ι������ΰ��
				// ù�� ������ ���� ������� �����(1�������̴ϱ�) N-2���� + ������
				// �ι������� ��� �����(N-1), �����(N), ��ĭ �ǳʶ� N-3���
				dp[i] = Math.max(dp[i-2] + score[i], dp[i-3] + score[i] + score[i-1]);
			}
			System.out.println(dp[Case]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    static int stair(int n) {
    	if(n==1) {
    		dp[n]= score[n];
    		System.out.println(dp[n]);
    		return dp[n];
    	}
    	if(n==2) return dp[n]= score[n];
    	dp[n] = Math.max(stair(n-1) + score[n], stair(n-2) + score[n]);
    	return dp[n];
    }
}