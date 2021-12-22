package DFS;
import java.io.*;
import java.util.*;
// + Grape, BFS
public class Back1260 {
	static ArrayList<ArrayList<Integer>> edge;
	static boolean[] visit;
	static int[] result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		edge = new ArrayList<>();
		for(int i=0; i<N+1; i++) {
			edge.add(new ArrayList<Integer>());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edge.get(a).add(b);
			edge.get(b).add(a);
			
		}
		for(ArrayList<Integer> i : edge) {
			Collections.sort(i);
		}
//		for(int i = 0; i<N+1; i++) {
//			Collections.sort(edge.get(i));			
//		}
//		System.out.println(edge);
			visit = new boolean[N+1];
			dfs(V);
			System.out.println();
			visit = new boolean[N+1];
			bfs(V);

		return;
	}
	private static void dfs(int start) {
//		if(count>V) return true;
		
		visit[start] = true;
		
		System.out.print(start + " ");
		for(int i : edge.get(start)) {
			if(!visit[i]) dfs(i);
		}
	}
	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(start);
		visit[start] = true;
		System.out.print(start + " ");
		
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			
			
			for(int i : edge.get(temp)) {
				if(!visit[i]) {
					queue.offer(i);
					visit[i] = true;
					System.out.print(i + " ");
				}
			}
		}
	}
}