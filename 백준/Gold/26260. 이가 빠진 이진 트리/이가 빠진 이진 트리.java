import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, deletedNode;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            int n = Integer.parseInt(tk.nextToken());
            if (n == -1)
                deletedNode = i;
            nums[i] = n;
        }

        nums[deletedNode] = Integer.parseInt(br.readLine());
        Arrays.sort(nums);

        printTree(0, N-1);

        bw.flush();
    }

    private static void printTree(int start, int end) throws IOException {
        if (start == end){
            bw.write(nums[start]+" ");
            return;
        }
        int mid = (start + end) / 2;
        printTree(start, mid-1);
        printTree(mid+1, end);
        bw.write(nums[mid]+" ");
    }
}