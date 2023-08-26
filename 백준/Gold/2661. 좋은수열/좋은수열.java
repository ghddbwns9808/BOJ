import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n;
    static int[] num;
    static boolean solve = false;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        num = new int[n];

        backtracking(0);
    }
    private static void backtracking(int curIdx) throws IOException {
        //찾았다!
        if(curIdx == n) {
            for(int i: num)
                bw.write(i+"");
            bw.flush();
            solve = true;
            return;
        }

        for(int i=1; i<=3; i++) {
            num[curIdx] = i;
            if(isGoodNumbers(curIdx)) {
                backtracking(curIdx+1);
                if(solve)
                    return;
            }
        }
    }

    private static boolean isGoodNumbers(int lastIdx) {
        for(int i=1; i<=lastIdx/2 + lastIdx%2; i++) {
            boolean same= true;
            for(int j=0; j<i; j++) {
                //System.out.println("num[lastIdx -j], num[lastIdx-i-j]: "+ num[lastIdx -j]+", "+ num[lastIdx -j-i]);
                if(num[lastIdx -j] != num[lastIdx-i-j])
                    same = false;
            }
            if (same)
                return false;
        }
        return true;
    }
}
