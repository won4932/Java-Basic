package grid;
import java.util.Scanner;

public class Back2138 {
	static int size, count = 0; 
	static boolean[] before, after, copy;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String temp = "";
		size = N;
		before = new boolean[size];
		after = new boolean[size];
		copy = new boolean[size];
		temp = sc.next();
		for(int i=0; i<size; i++) {
			if(temp.charAt(i)=='1') {
				before[i] = true;
			}else {
				before[i] = false;
			}
			
		}
		temp = sc.next();
		for(int i=0; i<size; i++) {
			if(temp.charAt(i)=='1') {
				after[i] = true;				
			}else {
				after[i] = false;
			}
		}
		go();
		
		
	}
	private static void go() {
		if(posible()) {
			System.out.println(count);
			return;
		}
		// 첫번쨰 누르고 시작
		change(1, false);
		count=1;
		if(posible()) {
			System.out.println(count);
			return;
		}
		System.out.println(-1);
	}
	private static boolean posible() {
		copy = before.clone();
		for(int i=1; i<size-1; i++) {
			if(copy[i-1]!= after[i-1]) {
				count++;
				change(i, true);
			}
		}
		if(copy[size-2]!= after[size-2]) {
			copy[size-2]= after[size-2];
			if(copy[size-1]) {
				copy[size-1] = false;
			}else {
				copy[size-1] = true;
			}
			count++;
		}
		for(int i = 0; i<size; i++) {
			if(copy[i]!= after[i]) return false;
		}
		return true;
		
	}
	private static void change(int index, boolean boo) {
		if(boo) { // 첫번쨰 안누를시
			for(int i = 0; i<3; i++) {
				if(copy[index-1 + i]) {
					copy[index-1 + i] = false;
				}else {
					copy[index-1 + i] = true;
				}
			}
		}else { // 누르고 시작
			for(int i = 0; i<2; i++) {
				if(before[index-1 + i]) {
					before[index-1 + i] = false;
				}else {
					before[index-1 + i] = true;
				}
			}
		}
	}
}

//public class Back2138 {
//	static int[] before;
//	static int[] after;
//	static int[] paste;
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int N = sc.nextInt();
//		before = new int[N];
//		after = new int[N];
//		paste = new int[N];
//		String temp = "";
//		int result = 0;
//		int result2 = 0;
//	
//		
//		temp = sc.next();
//		for(int j=0; j<N; j++) {
//			before[j] = temp.charAt(j)-'0';
//		}
//		temp = sc.next();
//		for(int j=0; j<N; j++) {
//			after[j] = temp.charAt(j)-'0';
//		}
//
//		paste = before.clone();
//		paste[0] = 1-paste[0];
//		paste[1] = 1-paste[1];
//		
//		result = push(paste, 1);
//		result2 = push(before, 0);
//		if(result == -1) result = result2;
//		
//		
//		System.out.println(result);
//		
//		
//		
//		
//	}
//	
//
//	public static int push(int temp[], int sw) {
//		int count = sw;
//		for(int i = 1; i<temp.length-1; i++) {
//			if(temp[i-1]!=after[i-1]) {
//				count++;
//				temp[i] = 1-temp[i];
//				temp[i-1] = 1-temp[i-1];
//				temp[i+1] = 1-temp[i+1];
//			}
//			
//		}
//		if(temp[temp.length-2]!=after[temp.length-2]) {
//			count++;
//			temp[temp.length-2] = 1-temp[temp.length-2];
//			temp[temp.length-1] = 1-temp[temp.length-1];
//		}
//		
//		for(int i=0; i<temp.length; i++) {
//			if(temp[i]!=after[i]) count = -1;
//		}
//		return count;
//	}
//
//}
