import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, S, ans;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        input();
        if (nums[0] >= S) ans = 1;
        else solve();

        if (ans == Integer.MAX_VALUE) bw.write("0");
        else bw.write(ans+"");
        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        S = Integer.parseInt(tk.nextToken());
        ans = Integer.MAX_VALUE;

        nums = new int[N];

        tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            nums[i] = Integer.parseInt(tk.nextToken());
    }

    private static void solve(){
        int left = 0;
        int right = 1;
        int tmp = nums[left] + nums[right];

        while (left <= right && right<N){
            if (tmp >= S){
                ans = Math.min(ans, right-left+1);
                tmp -= nums[left];
                left++;
            }else{
                if (right == N-1)
                    break;
                right++;
                tmp += nums[right];
            }
        }
    }
}