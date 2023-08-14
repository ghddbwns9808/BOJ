import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int curDir = 0;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int[] xArr;
	static int[] yArr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine());
		char[] commands = br.readLine().toCharArray();
		
		xArr = new int[50]; yArr = new int[50];
		xArr[0] = 0; yArr[0] = 0;
		int idx = 1;
		
		for(int i=0; i<n; i++) {
			switch (commands[i]) {
			case 'F':
				xArr[idx] = xArr[idx-1] + dx[curDir];
				yArr[idx] = yArr[idx-1] + dy[curDir];
				idx++;
				break;
			case 'L':
				if(curDir == 0)
					curDir = 4;
				curDir--;
				break;
			case 'R':
				if(curDir == 3)
					curDir = -1;
				curDir++;
				break;
			}
		}
		
		//출력
		int maxX = Arrays.stream(xArr).max().getAsInt();
		int minX = Arrays.stream(xArr).min().getAsInt();
		int maxY = Arrays.stream(yArr).max().getAsInt();
		int minY = Arrays.stream(yArr).min().getAsInt();
		
		char[][] maze = new char[maxX - minX + 1][maxY - minY + 1];
		for(int i=0; i<maze.length; i++)
			Arrays.fill(maze[i], '#');
		for(int i=0; i<idx; i++) {
			maze[xArr[i] - minX][yArr[i] - minY] = '.';
		}
		
		for(int i=0; i<maze.length; i++) {
			for(int j=0; j<maze[0].length; j++)
				System.out.print(maze[i][j]);
			System.out.println();
		}
	}
}
