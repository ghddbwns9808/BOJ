import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int[][] board = new int[5][5];
    static Set<String> result = new HashSet<>();

    public static void main(String[] args) throws IOException {
        for(int i=0; i<5; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++)
                board[i][j] = Integer.parseInt(tk.nextToken());
        }

        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                int[] curResult = new int[6];
                Point[] cur = new Point[6];
                curResult[0] = board[i][j];
                cur[0] = new Point(i, j);
                dfs(1, curResult, cur);
            }
        }

        bw.write(result.size()+"");
        bw.flush();
    }

    private static void dfs(int idx, int[] curResult, Point[] cur){
        if(idx == 6){
            StringBuilder sb = new StringBuilder();
            for(int n: curResult)
                sb.append(n + "");
            result.add(sb.toString());
            return;
        }
        Point prev = cur[idx-1];

        for(int i=0; i<4; i++){
            int nx = prev.x + dx[i];
            int ny = prev.y + dy[i];

            if(isValidPosition(nx, ny)){
                cur[idx] = new Point(nx, ny);
                curResult[idx] = board[nx][ny];

                dfs(idx+1, curResult, cur);
            }
        }
    }

    private static boolean isValidPosition(int x, int y){
        return 0<=x && x<5 && 0<=y && y<5;
    }
}

class Point{
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
