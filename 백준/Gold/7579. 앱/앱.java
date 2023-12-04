import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, totalCost;
    static int[] cost, weight;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();

        int min = Integer.MAX_VALUE;
        for (int i=1; i<dp.length; i++){
            for (int j=0; j<dp[0].length; j++){
//                System.out.print(dp[i][j] + " ");
                if (dp[i][j] >= M){
                    min = Math.min(min, j);
                }
            }
//            System.out.println();
        }

        bw.write(min+"");
        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        cost = new int[N+1];
        weight = new int[N+1];

        tk = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++)
            weight[i] = Integer.parseInt(tk.nextToken());

        tk = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            cost[i] = Integer.parseInt(tk.nextToken());
            totalCost += cost[i];
        }

        dp = new int[N+1][totalCost+1];

    }

    private static void solve(){
        for (int i=1; i<=N; i++){
            for (int w=0; w<=totalCost; w++){
                if (cost[i] <= w){
                    if (weight[i] + dp[i-1][w - cost[i]] > dp[i-1][w]){
                        dp[i][w] = weight[i] + dp[i-1][w - cost[i]];
                    }else{
                        dp[i][w] = dp[i-1][w];
                    }
                }else{
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
    }
}