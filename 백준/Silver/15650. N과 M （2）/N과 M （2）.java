import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
 
public class Main {
 
	public static int[] numbers;
	public static int n;
    public static int m;
	public static StringBuilder sb = new StringBuilder();
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
 
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
 
		numbers = new int[m];
        
		dfs(1, 0);
		System.out.println(sb);
 
	}
 
	public static void dfs(int at, int depth) {
		if (depth == m) {
			for (int n : numbers) {
				sb.append(n).append(' ');
			}
			sb.append('\n');
			return;
		}
        
		for (int i = at; i <= n; i++) {
			numbers[depth] = i;
			dfs(i + 1, depth + 1);
		}
	}
}