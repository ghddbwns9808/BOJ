import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, S;
    static int[] nums;


    public static void main(String[] args) throws IOException {
        input();
        solve();
        for (int n: nums)
            bw.write(n + " ");
        bw.flush();
    }

    private static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            nums[i] = Integer.parseInt(tk.nextToken());
        S = Integer.parseInt(br.readLine());
    }

    private static void solve(){
        int sorted = 0;
        while (S > 0 && sorted < N){
            int maxIdx = findMaxIndex(sorted, Math.min(sorted + S, N-1));
            if (maxIdx != sorted)
                sort(maxIdx, sorted);
            sorted++;
        }
    }

    private static void swap(int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    private static int findMaxIndex(int from, int to){
        int max = Integer.MIN_VALUE;
        int idx = 0;

        for (int i = from; i<=to; i++){
            if (max < nums[i]) {
                idx = i;
                max = nums[i];
            }
        }

        return idx;
    }

    private static void sort(int target, int to){
        int i = target;
        while (i > to){
            swap(i, i-1);
            i--;
            S--;
        }
    }

}