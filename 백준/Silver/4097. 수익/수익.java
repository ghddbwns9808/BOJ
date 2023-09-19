import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dp, income;

    public static void main(String[] args) throws IOException {
        while (true){
            int n = Integer.parseInt(br.readLine());
            if (n == 0)
                break;
            dp= new int[n];
            income= new int[n];
            for (int i=0; i<n; i++)
                income[i] = Integer.parseInt(br.readLine());

            dp[0] = income[0];
            for (int i=1; i<n; i++)
                dp[i] = Math.max(dp[i-1] + income[i], income[i]);

            int maxIncome = Integer.MIN_VALUE;
            for (int i=0; i<n; i++)
                maxIncome = Math.max(maxIncome, dp[i]);
            bw.write(maxIncome + "\n");
        }
        bw.flush();
    }
}