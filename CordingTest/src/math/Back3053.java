package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//¼öÇÐ2
public class Back3053 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        	double R = Integer.parseInt(br.readLine());
            bw.write(String.format("%.6f", Math.PI*Math.pow(R, 2))+ "\n");
            bw.write(String.format("%.6f", 2*Math.pow(R, 2)) + "\n");
            bw.flush();
            br.close();
        bw.close();
    }
    
}