import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        parent = new int[N];
        for (int i=0; i<N; i++)
            parent[i] = i;

        boolean cycle = false;
        for (int i=1; i<=M; i++){
            tk = new StringTokenizer(br.readLine());
            if(!union(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()))){
                cycle = true;
                bw.write(i+"");
                break;
            }
        }
        if (!cycle)
            bw.write("0");
        bw.flush();
    }

    private static int find(int a){
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);

    }

    private static boolean union(int a, int b){
        int A = find(a);
        int B = find(b);

        if (A == B)
            return false;
        parent[B] = A;
        return true;
    }
}