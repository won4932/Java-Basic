package grid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Back12015 {
static int N=0;
static ArrayList<Integer> sequence;
public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	N = Integer.parseInt(br.readLine());
	sequence = new ArrayList<>(N);
	int result = 1;
	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
	for(int i =0; i<N; i++) {
		int temp = Integer.parseInt(st.nextToken());
		if(!sequence.contains(temp)) sequence.add(temp);
	}
	Collections.sort(sequence);
//	for(int i = 0; i<sequence.size(); i++) {
//		for(int j = i+1; j<sequence.size(); j++) {
//			int index = sequence.get(j) - sequence.get(i);
//			for(int k = j; k<)
//		}
//	}
	
	System.out.println(sequence.size());
//	for(int i : sequence) System.out.print(i + " ");
	}
}