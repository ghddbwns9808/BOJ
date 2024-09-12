import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, ans;
    static int[][] map;
    static boolean[][] vst;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        input();
        solve();
        bw.write(ans + "");
        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        map = new int[N][M];
        for (int i=0; i<N; i++){
            tk = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++)
                map[i][j] = Integer.parseInt(tk.nextToken());
        }
    }

    private static void solve(){
        while (true){
            vst = new boolean[N][M];
            if (!melt()){
                ans = 0;
                break;
            }

            ans++;

            int bingsan = 0;
            for (int i=0; i<N; i++){
                for (int j=0; j<M; j++){
                    if (!vst[i][j] && map[i][j] > 0){
                        bfs(i, j);
                        bingsan++;
                    }
                }
            }
            if (bingsan > 1) break;
        }
    }

    private static  boolean isValidPosition(int x, int y){
        return 0<=x && x<N && 0<=y && y<M;
    }

    private static boolean melt(){
        int[][] melt = new int[N][M];
        boolean flag = false;

        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                if (map[i][j] > 0){
                    flag = true;
                    int waterCount = 0;
                    for (int k=0; k<4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (isValidPosition(nx, ny) && map[nx][ny] == 0)
                            waterCount++;
                    }
                    melt[i][j] = waterCount;
                }
            }
        }

        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++)
                map[i][j] = Math.max(0, map[i][j] - melt[i][j]);
        }

        return flag;
    }

    private static void bfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        vst[x][y] = true;

        while (!q.isEmpty()){
            int[] cur = q.poll();

            for (int i=0; i<4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isValidPosition(nx, ny) && map[nx][ny] > 0 && !vst[nx][ny]){
                    q.offer(new int[]{nx, ny});
                    vst[nx][ny] = true;
                }
            }
        }
    }
}