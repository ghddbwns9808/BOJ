import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m, cnt, tc;
    static int[] parent;
    static HashSet<Integer> cycle;
    public static void main(String[] args) throws IOException {
        tc = 0;
        while (true){
            if(!input()) break;
            solve();

            bw.write("Case " + tc + ": ");
            switch (cnt){
                case 0:
                    bw.write("No trees.\n");
                    break;
                case 1:
                    bw.write("There is one tree.\n");
                    break;
                default:
                    bw.write("A forest of " + cnt + " trees.\n");
                    break;
            }
        }

        bw.flush();
    }

    private static boolean input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());

        if (n+m == 0) return false;
        tc++;

        cnt = 0;
        cycle = new HashSet<>();
        parent = new int[n+1];
        for (int i=1; i<=n; i++)
            parent[i] = i;

        for (int i=0; i<m; i++){
            tk = new StringTokenizer(br.readLine());
            union(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()));
        }
        return true;
    }

    private static void union(int a, int b){
        int A = find(a);
        int B = find(b);

        if (A != B){
            parent[A] = Math.min(A, B);
            parent[B] = Math.min(A, B);
        }else{
            parent[A] = 0;
            parent[B] = 0;
        }
    }

    private static int find(int a){
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void solve(){
        for (int i=1; i<=n; i++){
            if (parent[i] == i){
                cnt++;
            }
        }
    }
}
