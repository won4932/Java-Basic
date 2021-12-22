package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//¼öÇÐ
public class Back10250 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int H = Integer.parseInt(st.nextToken());
        	int W = Integer.parseInt(st.nextToken());
        	int N = Integer.parseInt(st.nextToken());
        	int floor = N%H, room = N/H+1;
        	if(N%H==0) {
                room=N/H;
                floor=H;
            }
            System.out.println(floor*100+room);
		}
        
    }
    
}