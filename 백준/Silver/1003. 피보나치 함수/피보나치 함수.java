import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int[][] dp = new int[41][2];

	public static void main(String[] args) throws IOException {
		int tc = Integer.parseInt(br.readLine());
		dp[0][0] = 1; dp[0][1] = 0;
		dp[1][0] = 0; dp[1][1] = 1;
		
		for(int i=0 ; i<tc; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n>1) 
				fibonacciDP(n);
			System.out.println(dp[n][0]+" "+dp[n][1]);
			
		}
	}
	
	private static void fibonacciDP(int n) {
		for(int i=2; i<=n; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-2][0];
			dp[i][1] = dp[i-1][1] + dp[i-2][1];
		}
	}
}