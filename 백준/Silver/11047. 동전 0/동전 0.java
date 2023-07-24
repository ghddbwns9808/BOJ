import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int[] money;
	public static void main(String[] args) throws IOException {
		StringTokenizer tk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(tk.nextToken());
		int k = Integer.parseInt(tk.nextToken());
		money = new int[n];
		
		int[] money = new int[n];
		for(int i=0; i<n; i++) 
			money[i] = Integer.parseInt(br.readLine());
		
		System.out.println(selectMoneyGreedy(money, k));
	
	}
	
	private static int selectMoneyGreedy(int[] money, int curMoney) {
		int remain = curMoney;
		int cnt = 0;
		
		for(int i=money.length-1; i>=0; i--) {
			while(remain >= money[i]) {
				remain -= money[i];
				cnt++;
			}
		}
		return cnt;
	}
}