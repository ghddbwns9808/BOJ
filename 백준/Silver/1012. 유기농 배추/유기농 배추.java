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
	
	private static int[] dx = new int[] {1, 0, -1, 0};
	private static int[] dy = new int[] {0, -1, 0, 1};
	
	private static int[][] board;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		int tc = Integer.parseInt(br.readLine());
		
		for(int c = 0; c<tc; c++) {
			tk = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(tk.nextToken());
			int n = Integer.parseInt(tk.nextToken());
			int k = Integer.parseInt(tk.nextToken());
			int cnt = 0;
			
			board = new int[n][m];
			visited = new boolean[n][m];
			
			for(int i=0; i<k; i++) {
				tk = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(tk.nextToken());
				int y = Integer.parseInt(tk.nextToken());
				board[y][x] = 1;
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(board[i][j] == 1 && !visited[i][j]) {
						dfs(new Point(i, j));
						cnt++;
					}
				}
			}
			System.out.println(cnt);
			
			
		}
	}
	
	private static void dfs(Point start) {
		Stack<Point> st = new Stack<>();
		st.add(start);
		visited[start.x][start.y] = true;
		
		while(!st.isEmpty()) {
			Point cur = st.pop();
			int x = cur.x;
			int y = cur.y;
			
			for(int i=0; i<4; i++) {
				int movedX = x + dx[i];
				int movedY = y + dy[i];
				
				if(0<=movedX && movedX<board.length && 0<=movedY && movedY<board[0].length
						&& !visited[movedX][movedY] && board[movedX][movedY] == 1) {
					visited[movedX][movedY] = true;
					st.add(new Point(movedX, movedY));
				}
			}
		}
	}
	
}

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}