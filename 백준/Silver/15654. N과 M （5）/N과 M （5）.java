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
	
	private static int[] numbers; 
	private static boolean[] visited;
	private static int n;
	private static int m;
	
	public static void main(String[] args) throws IOException {
		tk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(tk.nextToken());
		m = Integer.parseInt(tk.nextToken());
	 
		numbers = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt).toArray();
		
		Arrays.sort(numbers);
		visited = new boolean[numbers[n-1]+1];
		
		for(int i=0; i<n; i++) {
			int[] cur = new int[m];
			cur[0] = numbers[i];
			visited[numbers[i]] = true;
			nmRec(cur, 1);
			visited[numbers[i]] = false;
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
		for (int i = 0; i < n; i++) {
			if (!visited[numbers[i]]) {
				cur[depth] = numbers[i];
				visited[numbers[i]] = true;
				nmRec(cur, depth + 1);
				visited[numbers[i]] = false;
			}
		}
		return;	
	}
}