import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dp;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i=2; i<=n; i++)
            dp[i] = (dp[i-1] + dp[i-2]) % 15746;

        bw.write(dp[n]+"");
        bw.flush();
    }
}