import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, S, cnt;
    static int[] nums;
    static boolean[] vst;

    public static void main(String[] args) throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        S = Integer.parseInt(tk.nextToken());
        cnt = 0;
        nums = new int[N];
        vst = new boolean[N];

        tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            nums[i] = Integer.parseInt(tk.nextToken());

        backtracking(0, 0);
        if (S == 0) cnt--;
        bw.write(cnt + "");
        bw.flush();
    }

    private static void backtracking(int start, int sum){
        if (sum == S){
            cnt++;
        }

        for (int i=start; i<N; i++){
            backtracking(i+1, sum + nums[i]);
        }
    }

}