import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<Edge> edges = new ArrayList<>();
    static int n;

    static int[][] planets;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        planets = new int[n][n];
        parent = new int[n+1];

        for(int i=0; i<n; i++)
            parent[i] = i;

        for (int i=0; i<n; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++)
                planets[i][j] = Integer.parseInt(tk.nextToken());
        }

        for(int i=0; i<n-1; i++){
            for (int j=i+1; j<n; j++){
                edges.add(new Edge(i, j, planets[i][j]));
            }
        }

        Collections.sort(edges);

        long total = 0;
        for (Edge e: edges) {
            if (find(e.to) != find(e.from)) {
                total += e.weight;
                union(e.to, e.from);
            }
        }
        bw.write(total + "");
        bw.flush();
    }

    private static int find(int v){
        if (parent[v] == v)
            return v;

        return parent[v] = find(parent[v]);
    }

    private static void union(int v1, int v2){
        int V1 = find(v1);
        int V2 = find(v2);

        if (V1 != V2)
            parent[V2] = V1;
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    public Edge(int f, int t, int w) {
        this.from = f;
        this.to = t;
        this.weight = w;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }

    @Override
    public String toString() {
        return "E[" + from + ", " + to + ", " + weight + "]";
    }
}