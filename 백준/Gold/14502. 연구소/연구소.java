import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, ans;
    static int[][] map, copyMap;
    static boolean[][] visited;
    static ArrayList<Point> blankPoints, virus;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(ans+"");
        bw.flush();
    }

    static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        blankPoints = new ArrayList<>();
        virus = new ArrayList<>();
        ans = Integer.MIN_VALUE;

        for (int i=0; i<N; i++){
            tk = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(tk.nextToken());
                if (map[i][j] == 2)
                    virus.add(new Point(i, j));
                else if (map[i][j] == 0){
                    blankPoints.add(new Point(i, j));
                }
            }
        }
    }

    static void solve(){
        for (int i=0; i<blankPoints.size()-2; i++){
            for (int j=i+1; j<blankPoints.size()-1; j++){
                for (int k=j+1; k<blankPoints.size(); k++){
                    bfs(i, j, k);
                }
            }
        }
    }

    static void bfs(int a, int b, int c){
        copyMap = new int[N][M];
        for (int i=0; i<N; i++){
            copyMap[i] = map[i].clone();
        }
        copyMap[blankPoints.get(a).x][blankPoints.get(a).y] = 1;
        copyMap[blankPoints.get(b).x][blankPoints.get(b).y] = 1;
        copyMap[blankPoints.get(c).x][blankPoints.get(c).y] = 1;

        visited = new boolean[N][M];

        Queue<Point> q = new ArrayDeque<>();
        q.addAll(virus);

        while (!q.isEmpty()){
            Point cur = q.poll();

            for (int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isValidPoint(nx, ny) && copyMap[nx][ny] == 0 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    copyMap[nx][ny] = 1;
                    q.offer(new Point(nx, ny));
                }
            }
        }

        countSafeArea();
    }

    static void countSafeArea(){
        int cnt = 0;
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                if (copyMap[i][j] == 0)
                    cnt++;
            }
        }
        ans = Math.max(ans, cnt);
    }

    static boolean isValidPoint(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < M;
    }

}

class Point{
    int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}