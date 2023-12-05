import org.w3c.dom.xpath.XPathResult;

import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int G, P;
    static int[] airplane, parent;

    public static void main(String[] args) throws IOException {
        input();
        bw.write(solve()+"");
        bw.flush();
    }

    private static void input() throws IOException{
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        airplane = new int[P];
        for (int i=0; i<P; i++)
            airplane[i] = Integer.parseInt(br.readLine());

        parent = new int[G+1];
        for (int i=0; i<=G; i++)
            parent[i] = i;
    }

    private static int solve(){
        int cnt=0;
        for (int i=0; i<P; i++){
            int p = find(airplane[i]);
            if (p == 0) break;
            union(p, p-1);
            cnt++;
        }
        return cnt;
    }

    private static void union(int a, int b){
        int A = find(a);
        int B = find(b);

        if (A > B)
            parent[A] = B;
        else
            parent[B] = A;
    }

    private static int find(int a){
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}