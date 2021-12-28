package binarySearch;
import java.io.*;
import java.util.*;

// trying
public class Back1939 {
	static int bridge[][];
	static int len[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // ¼¶
		int M = Integer.parseInt(st.nextToken());
		bridge = new int[N][N];
		len = new int[M];
		int temp[] = new int[3];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<3; j++) {
				temp[j] = Integer.parseInt(st.nextToken());
			}
			len[i] = temp[2];
			if(bridge[temp[0]][temp[1]]<temp[2]) {
				bridge[temp[0]][temp[1]] = temp[2];
				bridge[temp[1]][temp[0]] = temp[2];
			}
		}
		Arrays.sort(len);
		int left = 1;
		int right = len[M-1];
		int result = 0;
		while(left<=right) {
			int mid = (left+right)/2;
			int len = division(mid);
			
			if(len>=N) {
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
		int start = len[0];
		for(int i : len) {
			int temp = i-start;
			if(temp>=n) {
				count++;
				start = i;
			}
		}
		return count;
	}
}