import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<Edge> edges = new ArrayList<>();
    static int n, m;
    static int[] parent;
    static String[] uni;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());

        parent = new int[n+1];
        visited = new boolean[n+1];
        for(int i=1; i<=n; i++)
            parent[i] = i;

        uni = br.readLine().split(" ");

        for (int i=0; i<m; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            if (!uni[from-1].equals(uni[to-1])) {
                edges.add(new Edge(from, to, weight));
                edges.add(new Edge(to, from, weight));
            }
        }

        Collections.sort(edges);

        long total = 0;
        for (Edge e: edges) {
            if (find(e.to) != find(e.from)) {
                visited[e.to] = true;
                visited[e.from] = true;
                total += e.weight;
                union(e.to, e.from);
            }
        }

        boolean connected = true;
        for (int i=1; i<=n; i++){
            if (!visited[i])
                connected = false;
        }

        if (connected) bw.write(total + "");
        else bw.write( "-1");
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