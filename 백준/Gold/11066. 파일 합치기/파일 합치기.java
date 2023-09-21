import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] dp, tmpSum;
    static int[] chapter;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T --> 0){
            int n = Integer.parseInt(br.readLine());
            dp = new int[n][n];
            tmpSum = new int[n][n];
            chapter = new int[n];

            for (int i=0; i<n; i++)
                Arrays.fill(dp[i], Integer.MAX_VALUE);

            StringTokenizer tk = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++)
                chapter[i] = Integer.parseInt(tk.nextToken());

            //chapter: 챕터별 페이지 수 배열
            for (int i=0; i<n; i++) {
                tmpSum[i][i] = chapter[i];
                dp[i][i] = 0;
            }
            
            //tmpSum: i번째 chapter 부터 j번째 chapter 까지의 페이지 합
            for (int i=0; i<n-1; i++){
                for (int j=i+1; j<n; j++){
                     tmpSum[i][j] = tmpSum[i][j-1] + chapter[j];
                }
            }

            //dp: i번째 chapter 부터 j번째 chapter 까지 합치는데 필요한 최소 비용
            for (int i=1; i<n; i++){
                for (int j=0; j < n-i; j++){
                    for (int k=j; k< i+j; k++){
                        dp[j][i+j] = Math.min(dp[j][i+j], dp[j][k] + dp[k+1][i+j] + tmpSum[j][i+j]);
                    }
                }
            }
            bw.write(dp[0][n-1] +"\n");
        }
        bw.flush();
    }
}