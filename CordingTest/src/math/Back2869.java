package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//����
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
        	// long�� ���ǽ� �ӵ� ����
//            long A = Long.parseLong(st.nextToken());
//            long B = Long.parseLong(st.nextToken());
//            long V = Long.parseLong(st.nextToken());
//            //��ó���� ��ü ���̿��� A�� ���ְ�(�Ϸ簡 �����ٰ� �����ϰ� ���信 1�� ������),
//            //���� ��(V-A)�� �Ϸ縶���� �̵��Ÿ�(A-B)�� ���� �־ ������ �ɸ����� ���ϸ� �Ǵµ�,
//            //������ ������ ���� 0�̸� ���� ���� �����ְ�, �׷��� ������ ���� �� 1�� �������.
//            long ans = 1 + (((V-A)%(A-B) == 0 ? (V-A)/(A-B) : ((V-A)/(A-B)) + 1));
            bw.write(day + "\n");
            bw.flush();
            br.close();
        bw.close();
    }
    
}