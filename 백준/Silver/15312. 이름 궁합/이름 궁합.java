import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] alphabet = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
    static int[] name1, name2;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        String name = br.readLine();
        String her = br.readLine();

        name1 = new int[name.length()];
        name2 = new int[name.length()];

        for(int i=0; i<name.length(); i++){
            name1[i] = alphabet[name.charAt(i) - 'A'];
            name2[i] = alphabet[her.charAt(i) - 'A'];
        }

        dp = new int[name1.length * 2][name1.length * 2];
        for (int i=0; i<dp.length; i++){
            if (i%2 == 0) dp[0][i] = name1[i/2];
            else dp[0][i] = name2[i/2];
        }

        for (int i=1; i<dp.length-1; i++){
            for (int j=0; j< dp.length-i; j++){
                dp[i][j] = (dp[i-1][j] + dp[i-1][j+1]) % 10;
            }
        }

        bw.write(dp[dp.length-2][0] + "" +dp[dp.length-2][1]);
        bw.flush();
    }
}