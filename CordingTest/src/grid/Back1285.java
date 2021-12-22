package grid;
import java.util.Scanner;

//public class Back1285 {
//	static int size, count = 0; 
//	static boolean[][] coin, copy;
//	static int T = 0, H = 0;
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int N = sc.nextInt();
//		String temp = "";
//		size = N;
//		coin = new boolean[size][size];
//		copy = new boolean[size][size];
//		for(int i=0; i<size; i++) {
//			temp = sc.next();
//			for(int j=0; j<size; j++) {
//				if(temp.charAt(j)=='T') // T면 True H면 false
//					coin[i][j] = true;
//				}
//			}
//		count = Math.min(inspection(true), inspection(false));
//		
//		System.out.println(count);
//		}
//	private static int inspection(boolean flag) {
//		for(int i=0; i<size; i++) {
//            for(int j=0; j<size; j++) {
//                copy[i][j] = coin[i][j];  
//            }
//        }
//		int result = 0;
//		if(flag) {
//				horizon(flag); 
//				flag = false;
//				vertical(flag);
//			}else {
//				vertical(flag);
//				flag = true;
//				horizon(flag);
//			}
//		for(int i=0; i<size; i++) {
//			for(int j=0; j<size; j++) {
//				if(copy[i][j]) // T면 True H면 false
//					result++;
//				}
//			}
//		return result;
//		}
//	
//	private static void flip(int index, boolean flag) {
//		if(flag) {
//			for(int i = 0; i<size; i++) {
//				if(copy[index][i]) {
//					copy[index][i] = false;
//				}else {
//					copy[index][i] = true;
//				}
//			}
//		}else {
//			for(int i = 0; i<size; i++) {
//				if(copy[i][index]) {
//					copy[i][index] = false;
//				}else {
//					copy[i][index] = true;
//				}
//			}
//		}
//	}
//	private static void horizon(boolean flag) {
//		for(int i=0; i<size; i++) {
//			for(int j=0; j<size; j++) {
//					if(copy[i][j]) {
//						T++;
//					}else {
//						H++;
//					}
//			}
//			if(T>=H) {
//				flip(i, flag);
//			}
//			T=0;
//			H=0;
//			
//		}
//	}
//	private static void vertical(boolean flag) {
//		for(int i=0; i<size; i++) {
//			for(int j=0; j<size; j++) {
//				if(copy[j][i]) {
//					T++;
//				}else {
//					H++;
//				}
//			}
//			if(T>=H) {
//				flip(i, flag);
//			}
//		}
//		T=0;
//		H=0;
//	}
//}

public class Back1285 {
static int count = 0; 
static boolean[][] coin;
static boolean[] row;
static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		coin = new boolean[N][N];
		row = new boolean[N];
		String temp = "";
		for(int i = 0; i<N; i++) {
			temp = sc.next();
			for(int j = 0; j<N; j++) {
				if(temp.charAt(j)=='T') {
					coin[i][j]=true;					
				}else{
					coin[i][j]=false;
				}
			}
		}
		simulation();
	}
	private static void simulation() {
		int result = N*N;
		int cases = (int) Math.pow(2, N)-1;
		for(int i = 0; i<cases; i++) {
			int sum = 0;
			String num = Integer.toBinaryString(i);
			trans(num);
			for(int j = 0; j<N; j++) {
				int nowT = 0;
				for(int k = 0; k<N; k++) {
					boolean now = coin[k][j];
					if(row[k]) {
						if(now) now = false;
						else now = true;
//						System.out.println("flip!");
					}
					if(now) nowT++;
//					System.out.println(k + " " + j + " " + now + " " + nowT);
				}
				sum += Math.min(nowT, N-nowT);
			}
			result = Math.min(result, sum);
			
		}
		System.out.println(result);
	}
	private static void trans(String nowRow) {
		while(nowRow.length()<N) {
			nowRow = "0" + nowRow;
		}
		for(int i = 0; i<N; i++) {
			char temp = nowRow.charAt(i);
			if(temp=='1') {
				row[N-i-1] = true;
			}else {
				row[N-i-1] = false;
			}
	}
}
}
