import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n,m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());

        parent = new int[n+1];
        for(int i=0; i<=n; i++)
            parent[i] = i;

        for(int i=0; i<m; i++){
            tk = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(tk.nextToken());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            if(command == 0){
                if(find(a) != find(b)) {
                    if (a >= b) union(a, b);
                    else union(b, a);
                }
            }
            else{
                if (find(a) == find(b)) bw.write("YES\n");
                else bw.write("NO\n");
            }
        }
        bw.flush();
    }

    private static int find(int a){
        if(a == parent[a])
            return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b){
        int A = find(a);
        int B = find(b);
        if (A != B)
            parent[B] = A;
    }

}