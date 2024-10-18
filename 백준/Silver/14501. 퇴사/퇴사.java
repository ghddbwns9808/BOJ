import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, ans;
    static int[] T, P, dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();


        bw.write(ans + "");
        bw.flush();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        dp = new int[N+1];

        for (int i=0; i<N; i++) {
            StringTokenizer tk = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(tk.nextToken());
            P[i] = Integer.parseInt(tk.nextToken());
        }
    }

    private static void solve(){
        for (int i=0; i<N; i++){
            int nxt = i + T[i];
            dp[i] = Math.max(ans, dp[i]);
            if (nxt <= N){
                dp[nxt] = Math.max(dp[nxt], dp[i] + P[i]);
            }
            ans = Math.max(ans, dp[i]);
        }
        ans = Math.max(ans, dp[N]);
    }
}
