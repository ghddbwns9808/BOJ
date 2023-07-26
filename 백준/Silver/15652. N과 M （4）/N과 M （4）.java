import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tk;
	
	private static int[] visited; 
	private static int n;
	private static int m;
	
	public static void main(String[] args) throws IOException {
		tk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(tk.nextToken());
		m = Integer.parseInt(tk.nextToken());
	
		for(int i=1; i<=n; i++) {
			int[] cur = new int[m];
			cur[0] = i;
			nmRec(cur, 1);
		}
	}
	
	private static void nmRec(int[] cur, int depth) {
		if(depth == m) {
			StringBuilder sb = new StringBuilder();
			for(int n : cur) 
				sb.append(n+" ");
			System.out.println(sb);
			return;
		}
		int minNum = cur[depth-1];
		for(int i = minNum; i <= n; i++) {
			cur[depth] = i;
			nmRec(cur, depth+1);
		}
		return;	
	}
	
}