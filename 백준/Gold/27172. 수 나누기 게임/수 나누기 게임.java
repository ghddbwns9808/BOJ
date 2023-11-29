import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    private static final int MAX = 1000001;
    static int[] nums, score;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        visited = new boolean[MAX];
        score = new int[MAX];

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(tk.nextToken());
            visited[nums[i]] = true;
        }

        for (int i=0; i<N; i++){
            int j=2;
            while (nums[i] * j < MAX){
                if (visited[nums[i] * j]){
                    score[nums[i]]++;
                    score[nums[i] * j]--;
                }
                j++;
            }
        }

        for (int i=0; i<N; i++)
            bw.write(score[nums[i]] + " ");
        bw.flush();

    }
}