import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, ans;
    static boolean[][] map, vst;
    static List<int[]> boundary;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(ans+"");
        bw.flush();
    }

    private static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        vst = new boolean[N][N];
        ans = -1;
        boundary = new ArrayList<>();

        for (int i=0; i<N; i++){
            String line = br.readLine();
            for (int j=0; j<N; j++)
                map[i][j] = Character.getNumericValue(line.charAt(j)) == 1;
        }
    }

    private static void solve(){
        while (!vst[N-1][N-1]){
            vst = new boolean[N][N];
            ans++;
            bfs();
            removeBoundary();
        }
    }

    private static void bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        vst[0][0] = true;

        while (!q.isEmpty()){
            int[] cur = q.poll();

            for (int i=0; i<4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (isValidPosition(nx, ny)){
                    if (!map[nx][ny])
                        boundary.add(new int[]{nx, ny});
                    else{
                        q.offer(new int[]{nx, ny});
                        vst[nx][ny] = true;
                    }
                }
            }
        }
    }

    private static void removeBoundary(){
        for (int[] b: boundary){
            map[b[0]][b[1]] = true;
        }
    }

    private static boolean isValidPosition(int x, int y){
        return x>=0 && x<N && y>=0 && y<N && !vst[x][y];
    }
}
