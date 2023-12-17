import java.io.*;
import java.net.Inet4Address;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, cnt;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        input();
        floydWarshall();

        for (int i=1; i<=N; i++){
            if(check(i))
                cnt++;
        }
        bw.write(cnt+"");
        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        dist = new int[N+1][N+1];
        for (int i=1; i<=N; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);

        for (int i=0; i<M; i++){
            tk = new StringTokenizer(br.readLine());
            dist[Integer.parseInt(tk.nextToken())][Integer.parseInt(tk.nextToken())] = 1;
        }
    }

    private static void floydWarshall(){
        for (int i=1; i<=N; i++){
            for (int j=1; j<=N; j++){
                if (dist[j][i] == Integer.MAX_VALUE) continue;

                for (int k=1; k<=N; k++){
                    if (dist[i][k] == Integer.MAX_VALUE) continue;
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }
    }

    private static boolean check(int node){
        boolean[] canKnow = new boolean[N+1];
        canKnow[node] = true;
        for (int i=1; i<=N; i++){
            if (i == node) continue;
            if (dist[i][node] == Integer.MAX_VALUE && dist[node][i] == Integer.MAX_VALUE)
                return false;
        }
        return true;
    }

}