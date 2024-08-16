import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] P;
    static int N;

    public static void main(String[] args) throws IOException{
        input();
        solve();

        bw.write(solve()+"");
        bw.flush();
    }

    public static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        P = new int[N];

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            P[i] = Integer.parseInt(tk.nextToken());
    }

    public static long solve(){
        Arrays.sort(P);
        long ret = 0;
        for (int i=0; i<N; i++)
            ret += (long) (N - i) * P[i];

        return ret;
    }
}