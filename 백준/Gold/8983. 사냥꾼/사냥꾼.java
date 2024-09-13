import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int M, N, L, ans;
    static int[] shoot;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(ans + "");
        bw.flush();
    }

    private static void input() throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        M = Integer.parseInt(tk.nextToken());
        N = Integer.parseInt(tk.nextToken());
        L = Integer.parseInt(tk.nextToken());

        shoot = new int[M];
        tk = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            shoot[i] = Integer.parseInt(tk.nextToken());

        Arrays.sort(shoot);
    }

    private static void solve() throws IOException {
        while (N -- > 0){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tk.nextToken());
            int y = Integer.parseInt(tk.nextToken());

            int shootPoint = findNearestShoot(x, y);
            if (Math.abs(shoot[shootPoint] - x) + y <= L)
                ans++;
        }
    }

    private static int findNearestShoot(int x, int y){
        int left = 0;
        int right = M;

        if (x <= shoot[0])
            return 0;
        if (x >= shoot[M-1])
            return M-1;

        while (left <= right){
            int mid = (left + right) / 2;

            if (shoot[mid]  == x){
                return mid;
            } else if (shoot[mid] > x){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        if (x - shoot[right] >= shoot[right+1] - x)
            return right+1;
        return right;
    }
}