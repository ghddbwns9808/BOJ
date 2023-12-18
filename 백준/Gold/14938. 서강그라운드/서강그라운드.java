import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, R, ans;
    static int[][] dist;
    static int[] items;

    public static void main(String[] args) throws IOException {
        input();
        floydWarshall();
        solve();
        bw.write(ans+"");
        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        R = Integer.parseInt(tk.nextToken());

        ans = Integer.MIN_VALUE;

        dist = new int[N+1][N+1];
        items = new int[N+1];

        tk = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            items[i] = Integer.parseInt(tk.nextToken());
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i=0; i<R; i++){
            tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());
            int l = Integer.parseInt(tk.nextToken());

            dist[a][b] = l;
            dist[b][a] = l;
        }
    }

    private static void solve(){
        for (int i=1; i<=N; i++){
            int tmp = 0;
            for (int j=1; j<=N; j++){
                if (dist[i][j] <= M){
                    tmp += items[j];
                }
            }
            ans = Math.max(ans, tmp);
        }
    }

    private static void floydWarshall(){
        for (int j=1; j<=N; j++){
            for (int i=1; i<=N; i++){
                if (dist[i][j] == Integer.MAX_VALUE) continue;
                for (int k=1; k<=N; k++){
                    if (dist[j][k] == Integer.MAX_VALUE) continue;

                    dist[i][k] = Math.min(dist[i][k], dist[i][j] + dist[j][k]);
                }
            }
        }
    }

}