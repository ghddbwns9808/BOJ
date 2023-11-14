import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, weightSum;
    static List<Edge> edges;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] parent;

    public static void main(String[] args) throws IOException {
        input();
        kruskal();
        bw.write(weightSum+"");
        bw.flush();
    }

    private static void input() throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        weightSum = 0;
        edges = new ArrayList<>();

        parent = new int[N+1];
        for (int i=1; i<=N; i++)
            parent[i] = i;

        for (int i=0; i<M; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());
            boolean matched = Integer.parseInt(tk.nextToken()) == 1;

            if (matched) 
                union(from, to);

            if (!matched){
                edges.add(new Edge(from, to, weight));
                weightSum += weight;
            }
        }
    }

    static void kruskal(){
        Collections.sort(edges);

        for (Edge e: edges){
            if (find(e.from) != find(e.to)){
                union(e.from, e.to);
                weightSum -= e.weight;
            }
        }
    }

    static int find(int a){
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);

    }

    static void union(int a, int b){
        int A = find(a);
        int B = find(b);
        if (A!=B)
            parent[B] = A;
    }

}

class Edge implements Comparable<Edge>{
    int from, to, weight;

    public Edge(int f, int t, int w){
        from = f;
        to = t;
        weight = w;
    }

    @Override
    public int compareTo(Edge edge) {
        return edge.weight - weight;
    }

    @Override
    public String toString() {
        return "Edge[ from: " + from + ", to: " + to + ", weight: " + weight + "]";
    }
}