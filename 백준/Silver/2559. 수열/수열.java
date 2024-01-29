import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K, ans;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(ans+"");
        bw.flush();
    }

    static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());

        nums = new int[N];
        tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            nums[i] = Integer.parseInt(tk.nextToken());
    }

    static void solve(){
        int tmp = 0;
        for (int i=0; i<K; i++)
            tmp += nums[i];
        ans = tmp;



        int end = K;
        for (int start=0; start<N-K; start++){
            tmp -= nums[start];
            tmp += nums[end];
            ans = Math.max(ans, tmp);
            end++;
        }
    }

}