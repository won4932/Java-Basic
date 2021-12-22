package binarySearch;
import java.io.*;
import java.util.*;

public class Back1654 {
	static int get[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		get = new int[K];
		for(int i=0; i<K; i++) {
			get[i] = Integer.parseInt(br.readLine());
		}
//		Arrays.sort(get);
		long left = 1;
		long right = get[N-1];
//		int temp = 0;
		while(left<=right) {
			long mid = (left+right)/2;
			long len = division(mid);
			
			if(len<N) {
				right = mid -1;				
			}
			else {
//				if(mid>temp) temp = mid;
				left = mid + 1;
			}
		}
		System.out.println(right);
	}
	private static long division(long n) {
		long ans = 0;
		for(int i : get) {
			ans+= i/n;
		}
		return ans;
	}
}