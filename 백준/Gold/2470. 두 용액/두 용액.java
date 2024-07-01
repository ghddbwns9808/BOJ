import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] arr;
    static int N, ans, start, end;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(arr[start] + " " + arr[end]);
        bw.flush();
        bw.close();
    }

    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        ans = Integer.MAX_VALUE;

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            arr[i] = Integer.parseInt(tk.nextToken());

        Arrays.sort(arr);
    }

    static void solve(){
        int left = 0;
        int right = N-1;

        while (left < right){
            int sum = arr[left] + arr[right];
            if (Math.abs(sum) < ans){
                start = left;
                end = right;
                ans = Math.abs(sum);
            }

            if (sum == 0) break;
            else if (sum < 0) left++;
            else right--;
        }
    }
}