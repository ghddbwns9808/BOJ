import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] nums, prefixSum;
    static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer tk  = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        nums = new int[N];
        prefixSum = new int[N+1];

        tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            nums[i] = Integer.parseInt(tk.nextToken());

        prefixSum[0] = 0;
        for (int i=1; i<=N; i++)
            prefixSum[i] = prefixSum[i-1] + nums[i-1];

        while (M-->0){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken())-1;
            int to = Integer.parseInt(tk.nextToken());

            bw.write(prefixSum[to] - prefixSum[from] + "\n");
        }
        bw.flush();
    }
}
