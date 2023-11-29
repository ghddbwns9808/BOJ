import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static List<int[]> edges;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        input();

        for (int[] edge: edges)
            union(edge[0], edge[1]);

        int cnt = 0;
        for (int i=0; i<parent.length; i++){
            if (i == parent[i])
                cnt++;
        }

        bw.write(cnt+"");
        bw.flush();
    }

    private static void input() throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        parent = new int[N*M];
        edges = new ArrayList<>();

        for (int i=0; i<parent.length; i++)
            parent[i] = i;

        for (int i=0; i<N; i++){
            String input = br.readLine();
            for (int j=0; j<M; j++){
                int from = M*i + j;
                int to = -1;
                switch (input.charAt(j)) {
                    case 'U':
                        to = from - M;
                        break;
                    case 'D':
                        to = from + M;
                        break;
                    case 'L':
                        to = from - 1;
                        break;
                    case 'R':
                        to = from + 1;
                        break;
                };
                edges.add(new int[]{from, to});
            }
        }
    }

    private static int find(int a){
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);

    }

    private static void union(int a, int b){
        int A = find(a);
        int B = find(b);

        if (A!=B)
            parent[B] = A;
    }
}