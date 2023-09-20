import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] dp;
    static String input1, input2;
    public static void main(String[] args) throws IOException {
       input1 = br.readLine();
       input2 = br.readLine();

       dp = new int[input1.length()+1][input2.length()+1];

       for(int i=1; i<=input1.length(); i++){
           for (int j=1; j<=input2.length(); j++){
               if (input1.charAt(i-1) == input2.charAt(j-1)){
                   dp[i][j] = dp[i-1][j-1] + 1;
               }else{
                   dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
               }
           }
       }
       bw.write(dp[input1.length()][input2.length()] + "");
       bw.flush();
    }
}