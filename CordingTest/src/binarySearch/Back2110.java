package binarySearch;
import java.io.*;
import java.util.*;

public class Back2110 {
	static int get[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		get = new int[N];
		for(int i=0; i<N; i++) {
			get[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(get);
		int left = 1;
		int right = get[N-1]-get[0];
		int result = 0;
		while(left<=right) {
			int mid = (left+right)/2;
			int len = division(mid);
			
			if(len>=C) {
				result = mid;
				left = mid + 1;
			}
			else {
				right = mid -1;				
			}
		}
		System.out.println(result);
	}
	private static int division(int n) {
		int count = 1;
		int start = get[0];
		for(int i : get) {
			int temp = i-start;
			if(temp>=n) {
				count++;
				start = i;
			}
		}
		return count;
	}
}