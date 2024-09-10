import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, ans;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException{
        input();
        solve();
        bw.write(ans + "");
        bw.flush();
    }

    private static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for (int i=0; i<N; i++)
            pq.offer(Integer.parseInt(br.readLine()));
    }

    private static void solve(){
        while (true){
            int a = pq.poll();
            if (pq.isEmpty()) break;
            int b = pq.poll();
            ans += a+b;
            pq.offer(a+b);
        }
    }

}