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
	static int[][] img;
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		img = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<n; j++) 
				img[i][j] = line.charAt(j)-'0';
		}
		
		bw.write(dc(0, 0, n));
			
		br.close();
		bw.flush();
		bw.close();
	}
	
	private static String dc(int startX, int startY, int n) {
		if(isSameElementArray(startX, startY, n)) 
			return String.valueOf(img[startX][startY]);
		
		else {
			int newL = n/2;
			
			return "(" +
				dc(startX, startY, newL)+
				dc(startX, startY + newL, newL)+
				dc(startX + newL, startY, newL)+
				dc(startX + newL, startY + newL, newL)+
				")";
		}
	}
	
	private static boolean isSameElementArray(int startX, int startY, int n) {
		if(n == 1)
			return true;
		
		int key = img[startX][startY];
		for(int i=startX; i<startX + n; i++) {
			for(int j=startY; j<startY + n; j++) {
				if(img[i][j] != key)
					return false;
			}
		}
		return true;
	}
}