import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] nums;

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tk.nextToken());
        int m = Integer.parseInt(tk.nextToken());
        nums = new int[n];
        for (int i=0; i<n; i++)
            nums[i] = i+1;

        while (m-->0){
            tk = new StringTokenizer(br.readLine());
            swap(Integer.parseInt(tk.nextToken())-1, Integer.parseInt(tk.nextToken())-1);
        }

        for (int num: nums)
            bw.write(num + " ");
        bw.flush();
    }

    private static void swap(int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
