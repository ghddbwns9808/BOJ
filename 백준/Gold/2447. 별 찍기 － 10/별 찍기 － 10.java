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
	static char[][] img;
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		img = new char[n][n];
		
		for(int i=0; i<n; i++)
			Arrays.fill(img[i], ' ');
		
		dc(0, 0, n);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				sb.append(img[i][j]);
			}sb.append('\n');
		}
		System.out.println(sb);
		br.close();
	
	}
	
	private static void dc(int startX, int startY, int n) {
		if(n == 3) 
			drawBase(startX, startY);
		else {
			int newL = n/3;
			
			dc(startX, startY, newL);
			dc(startX, startY+newL, newL);
			dc(startX, startY+2*newL, newL);
			

			dc(startX+newL, startY, newL);
			dc(startX+newL, startY+2*newL, newL);
			
			
			dc(startX+2*newL, startY, newL);
			dc(startX+2*newL, startY+newL, newL);
			dc(startX+2*newL, startY+2*newL, newL);
		}
		
	}
	
	private static void drawBase(int startX, int startY) {
		for(int i=0; i<3; i++) {
			img[startX][startY + i] = '*';
			img[startX + 2][startY + i] = '*';
		}
		img[startX+1][startY] = '*';img[startX+1][startY+2] = '*';
	}
}
