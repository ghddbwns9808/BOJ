import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, D;
    static int[] dp;
    static List<ArrayList<int[]>> road;

    public static void main(String[] args) throws IOException{
        input();
        dp();
        bw.write(dp[D] + "");
        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        D = Integer.parseInt(tk.nextToken());
        dp = new int[D+1];
        road = new ArrayList<>();

        for (int i=0; i<=D; i++)
            road.add(new ArrayList<>());

        for (int i=0; i<N; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int w = Integer.parseInt(tk.nextToken());

            if (to <= D)
                road.get(to).add(new int[]{from, w});
        }
    }

    private static void dp(){
        for (int i=1; i<=D; i++){
            dp[i] = dp[i-1] + 1;
            for (int[] r: road.get(i)){
                dp[i] = Math.min(dp[i], dp[r[0]] + r[1]);
            }
        }
    }

}