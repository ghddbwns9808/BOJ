import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[][] dp;
    static int N, M;
    static int[] input;
    public static void main(String[] args) throws IOException {
        input();
        initDp();

        for (int i=N-2; i>=0; i--){
            for (int j=i+2; j<N; j++){
                if (input[i] == input[j] && dp[i+1][j-1])
                    dp[i][j] = true;
            }
        }

        M = Integer.parseInt(br.readLine());

        while (M --> 0){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken()) -1;
            int b = Integer.parseInt(tk.nextToken()) - 1;

            if (dp[a][b]) bw.write("1\n");
            else bw.write("0\n");
        }

        bw.flush();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        input = new int[N];

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            input[i] = Integer.parseInt(tk.nextToken());

        dp = new boolean[N][N];
    }

    private static void initDp(){
        for (int i=0; i<N; i++){
            dp[i][i] = true;
            if (i != N-1){
                if (input[i] == input[i+1])
                    dp[i][i+1] = true;
            }
        }
    }
}