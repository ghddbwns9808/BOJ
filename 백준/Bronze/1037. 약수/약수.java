import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            nums[i] = Integer.parseInt(tk.nextToken());
        Arrays.sort(nums);
        bw.write(nums[0] * nums[N-1] + "");
        bw.flush();
    }
}