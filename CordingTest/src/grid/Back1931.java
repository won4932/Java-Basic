package grid;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Back1931 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int meeting[][] = new int[N][2];
		int count = 1;
		int ing = 0;
		for(int i =0; i<N; i++) {
			meeting[i][0] = sc.nextInt(); // 시작
			meeting[i][1] = sc.nextInt(); // 끝
		}
		
		Arrays.sort(meeting, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				// o1[0], o2[0] = [i][0], 시작
				// o1[1], o2[1] = [i][1], 종료
				if(o1[1]==o2[1])
					return Integer.compare(o1[0], o2[0]);
				return Integer.compare(o1[1], o2[1]);
			}		
		});
		for(int i =1; i<meeting.length; i++) {
		if(meeting[ing][1] <= meeting[i][0]) {
			ing = i;
			count++;
			}
		}
		System.out.println(count);
//		for(int i = 0; i<meeting.length*meeting[0].length; i++) {
//			int row = i / meeting[0].length;
//			int col = i % meeting[0].length;
//			System.out.print(meeting[row][col] + " ");
//			
//			if(col == meeting[0].length -1) {
//				System.out.println();
//			}
//		}
	}

}
