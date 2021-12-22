package binarySearch;
import java.io.*;
import java.util.*;

public class Back1790 {
	//public static void main(String[] args) throws IOException {
//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//	int N = Integer.parseInt(st.nextToken());
//	int K = Integer.parseInt(st.nextToken());
//	long tmp = K;
//	long finalNum = 0;
//	long numlength = 1;
//	long numCnt = 9;
////	binarySearch(number, K);
////	System.out.println(number.charAt(23));
//	}
//private static void binarySearch(String s, int find) {
//	int left = 0;
//	int right = s.length()-1;
//	
//	while(left<right) {
//		int mid = (left + right)/2;
//		if(mid>find) {
//			right = mid;
//		}else if(mid<find) {
//			left = mid + 1;
//		}else {
//			System.out.println(s.charAt(mid));
//			return;
//		}
//	}
//}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(binarySearch(N) < K) {
			System.out.println(-1);
			return;
		}
		int left = 1;
		int right = N;
		int temp = 0;
		while(left<=right) {
			int mid = (left+right)/2;
			long len = binarySearch(mid);
			
			if(K>len) {
				left = mid + 1;
			}
			else {
				temp = mid;
				right = mid -1;
			}
		}
		String result = Integer.toString(temp);
		long index = binarySearch(temp);
		System.out.println(index + " " + temp);
		System.out.println(result.charAt((int) (result.length() - (index - K) - 1)));
//		System.out.println(result.charAt(0));
	}
	private static long binarySearch(int n) {
		long ans = 0;
		for (int start = 1, len = 1; start <= n; start *= 10, len++)
			while(start <= n) {
				int end = start * 10 -1;
				if(end>=n) {
					ans += (long) ((n - start + 1) * len);
				}else {
					ans += (long) ((end - start +1) * len);
				}
				start *= 10;
				len++;
			}
		return ans;
	}
}