package grid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Back2109 {
static int result = 0; 
static int[][] suggest;
static int N=0;
static boolean posibleDay[] = new boolean[100001];
public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;
	N = Integer.parseInt(br.readLine());
	suggest = new int[N][2];
	
	for(int i =0; i<N; i++) {
		st = new StringTokenizer(br.readLine(), " ");
		int p = Integer.parseInt(st.nextToken()); // M
		int d = Integer.parseInt(st.nextToken()); // K
		
		suggest[i][0] = p;
		suggest[i][1] = d;
	}
	
	Arrays.sort(suggest, new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			if(o1[0]==o2[0]) return Integer.compare(o1[1], o2[1]);
			return Integer.compare(o2[0], o1[0]);
		}
	});
	
	
	for(int i=0; i<N; i++) {
		for(int j=0; j<2; j++) {
			System.out.print(suggest[i][j] + " ");
		}
		System.out.println();
	}
	lecture();
	System.out.println(result);
}

private static void lecture() {
//	Queue<Integer> q = new PriorityQueue<>();
	// 시간효울성을 위해 우선순위 큐 사용
	// 삽입삭제 시 O(1)시간복잡도, O(가방개수 * 보석개수) -> O(가방개수 + 보석개수)
//	for(int i=0; i<K; i++) {
//		while(index < N && jewelry[index].weight <= bag[i]) {
//			q.offer(-jewelry[index].price);
//			index++;
//		}
//		
//		if(!q.isEmpty()) {
//			result +=Math.abs(q.poll());
//		}
//	}
	for(int i = 0; i<N; i++) {
		if(!posibleDay[suggest[i][1]]) {
			result += suggest[i][0];
			posibleDay[suggest[i][1]] = true;
		}else {
			for(int j = suggest[i][1]-1; j>=1; j--) {
				if(!posibleDay[j]) {
					result += suggest[i][0];
					posibleDay[j] = true;
					break;
				}
			}
		}
	}
}
}

//class Suggest implements Comparator<Suggest> {
//	int price;
//	int day;
//	
//	Suggest(int price, int day){
//		this.price = price;
//		this.day = day;
//	}
//
//	public int compare(Suggest o1, Suggest o2) {
//		// TODO Auto-generated method stub
//		if(o1.day==o2.day) return o1.price - o2.price;
//		return o1.day - o2.day;
//	}
//	
//	
//} 