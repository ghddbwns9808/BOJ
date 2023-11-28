import java.io.*;
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
                    if (dp[i-1][j] >= dp[i][j-1])
                        dp[i][j] = dp[i - 1][j];
                    else
                        dp[i][j] = dp[i][j-1];
                }
            }
        }

        int i = input1.length();
        int j = input2.length();
        Stack<Character> s = new Stack<>();

        while (i > 0 && j > 0){
            if (i == 0 || j == 0) break;

            if (dp[i][j] == dp[i-1][j]){
                i--;
            }else if(dp[i][j] == dp[i][j-1]){
                j--;
            }else{
                s.push(input1.charAt(i-1));
                i--;
                j--;
            }
        }
        bw.write(s.size()+"\n");
        while (!s.isEmpty()){
            bw.write(s.pop());
        }

        bw.flush();
    }
}