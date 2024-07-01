import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] dp;
    static int[] W, V;
    static int N, K;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(dp[N][K] + "");
        bw.flush();
        bw.close();
    }

    static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());
        dp = new int[N+1][K+1];
        W = new int[N+1];
        V = new int[N+1];

        for(int i=1; i<=N; i++){
            tk = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(tk.nextToken());
            V[i] = Integer.parseInt(tk.nextToken());
        }
    }

    static void solve(){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=K; j++){
                if(W[i] > j)
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]] + V[i]);
            }
        }
    }
}