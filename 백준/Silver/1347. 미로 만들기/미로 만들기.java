import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//방향을 제어할 변수 선언
	static int curDir = 0;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	//방문한 x좌표와 y좌표를 따로 저장
	static int[] xArr;
	static int[] yArr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine());
		char[] commands = br.readLine().toCharArray();
		
		//크기 50으로 선언
		xArr = new int[50]; yArr = new int[50];
		//0,0 에서 시작
		xArr[0] = 0; yArr[0] = 0;
		//추가는 1번 index 부터
		int idx = 1;
		
		for(int i=0; i<n; i++) {
			switch (commands[i]) {
			case 'F':
				//바라보고있는 방향으로 한칸 움직인 곳의 좌표를 x,y 좌표 따로 저장
				xArr[idx] = xArr[idx-1] + dx[curDir];
				yArr[idx] = yArr[idx-1] + dy[curDir];
				//다음 저장할 위치를 가리키는 idx
				idx++;
				break;
			//방향 전환(오버플로, 언더플로 유의)
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
		//가로, 세로의 최댓값 - 최솟값으로 미로의 크기 구하기
		int maxX = Arrays.stream(xArr).max().getAsInt();
		int minX = Arrays.stream(xArr).min().getAsInt();
		int maxY = Arrays.stream(yArr).max().getAsInt();
		int minY = Arrays.stream(yArr).min().getAsInt();
		
		char[][] maze = new char[maxX - minX + 1][maxY - minY + 1];
		//미로 #으로 초기화
		for(int i=0; i<maze.length; i++)
			Arrays.fill(maze[i], '#');
		
		//방문지점을 순회하며 좌표 - mininum 좌표값으로 0,0에서 시작하는 미로 그리기
		for(int i=0; i<idx; i++) 
			maze[xArr[i] - minX][yArr[i] - minY] = '.';
		
		//출력
		for(int i=0; i<maze.length; i++) {
			for(int j=0; j<maze[0].length; j++)
				System.out.print(maze[i][j]);
			System.out.println();
		}
	}
}
