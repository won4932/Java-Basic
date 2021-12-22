package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//수학
public class Back2869 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	int V = Integer.parseInt(st.nextToken());
//        	int result = 0;
//        	int day = 0;
//        	while(result < V) {
//        		day++;
//        		result+=A;
//        		if(result>=V) break;
//        		
//        		result-=B;
//        	}
        	//
        	int day = 1 + (((V-A)%(A-B) == 0 ? (V-A)/(A-B) : ((V-A)/(A-B)) + 1));
        	// long형 정의시 속도 증가
//            long A = Long.parseLong(st.nextToken());
//            long B = Long.parseLong(st.nextToken());
//            long V = Long.parseLong(st.nextToken());
//            //맨처음에 전체 길이에서 A를 빼주고(하루가 지났다고 생각하고 정답에 1을 더해줌),
//            //빼준 값(V-A)를 하루마다의 이동거리(A-B)로 나눠 주어서 몇일이 걸리는지 구하면 되는데,
//            //나눠준 나머지 값이 0이면 나눈 몫을 더해주고, 그렇지 않으면 나눈 몫에 1을 더해줬다.
//            long ans = 1 + (((V-A)%(A-B) == 0 ? (V-A)/(A-B) : ((V-A)/(A-B)) + 1));
            bw.write(day + "\n");
            bw.flush();
            br.close();
        bw.close();
    }
    
}