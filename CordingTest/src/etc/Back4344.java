package etc;
import java.util.Scanner;
//1차원 배열
public class Back4344 {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();
        sc.nextLine();
        int[] TestCase;
        float[] Result = new float[C];
        
        for (int i = 0; i < C; i++) {
        	int Sum=0;
			int N= sc.nextInt();
			TestCase = new int[N];
			for (int j = 0; j < N; j++) {
				TestCase[j] = sc.nextInt();
				Sum += TestCase[j];
			}
			sc.nextLine();
			int count=0;
			for(int e : TestCase) {
				if(e>(Sum/N)) count++;
			}
			Result[i] = (float)count/N*100;
			
		}
        for(float e : Result)
        	System.out.println(String.format("%.3f", e) + "%");
    }
}