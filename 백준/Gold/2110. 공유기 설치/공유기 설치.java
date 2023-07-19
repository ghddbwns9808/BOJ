import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int[] house;
	private static int n;
	private static int c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tk = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(tk.nextToken());
		c = Integer.parseInt(tk.nextToken());
		
		house = new int[n];

		for(int i=0; i<n; i++) 
			house[i] = Integer.parseInt(br.readLine());
		br.close();
		Arrays.sort(house);
		
		if(n==c) {
			int minDist = Integer.MAX_VALUE;
			for(int i=0; i<n-1; i++) {
				minDist = Math.min(minDist,house[i+1] - house[i]);
			}
			bw.write(minDist + "");
			bw.flush();
			bw.close();
			return;
		}
		
		
		int start = 1;
		int mid = 1;
		int end = house[n-1] - house[0];
		
		while (start <= end) {
			mid = (start + end) / 2;
			if(canInstall(house[0], mid)) {
				start = mid+1;
			}else {
				end = mid-1;
			}
		}
		bw.write(end+"");
		bw.flush();
		bw.close();
	}
	
	private static boolean canInstall(int start, int dist) {
		int cur = start;
		for(int i=0; i<c-1; i++) {
			cur += dist;
			cur = nextPoint(cur);
			if(cur == -1)
				return false;
		}
		return true;
	}
	
	private static int nextPoint(int minPoint) {
		int idx = 0;
		
		while(idx < n) {
			if(house[idx] >= minPoint) {
				return house[idx];
			}
			idx++;
		}
		return -1;
	}
}
