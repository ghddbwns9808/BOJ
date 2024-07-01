import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, p, q;
    static int[] packages, single;

    public static void main(String[] args) throws IOException {
        input();
        bw.write(solve() + "");

        bw.flush();
        bw.close();
    }

    static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        packages = new int[M];
        single = new int[M];

        p = Integer.MAX_VALUE;
        q = Integer.MAX_VALUE;

        for (int i=0; i<M; i++){
            tk = new StringTokenizer(br.readLine());
            packages[i] = Integer.parseInt(tk.nextToken());
            single[i] = Integer.parseInt(tk.nextToken());

            p = Math.min(p, packages[i]);
            q = Math.min(q, single[i]);
        }
    }

    static int solve(){
        if (p < 6*q){
            int pack = (N/6) * p;

            if (p > (N%6) * q){
                return pack + (N%6) * q;
            }else{
                return pack + p;
            }
        }else{
            return N * q;
        }
    }
}
