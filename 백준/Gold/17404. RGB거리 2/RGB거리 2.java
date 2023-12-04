import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, minCost;
    static int[][] cost, dp;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        minCost = Integer.MAX_VALUE;

        for (int i=0; i<N; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(tk.nextToken());
            cost[i][1] = Integer.parseInt(tk.nextToken());
            cost[i][2] = Integer.parseInt(tk.nextToken());
        }

        for(int start = 0; start<3; start++){
            dp = new int[N][3];

            for (int i=0; i<3; i++){
                if (start == i)
                    dp[0][i] = cost[0][i];
                else
                    dp[0][i] = 1001;
            }

            for (int i=1; i<N; i++){
                for (int j=0; j<3; j++){
                    dp[i][j] = Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]) + cost[i][j];
                }
            }

            for (int i=0; i<3; i++){
                if (start != i)
                    minCost = Math.min(minCost, dp[N-1][i]);
            }
        }
        bw.write(minCost+"");
        bw.flush();
    }
}