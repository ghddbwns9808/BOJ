import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<Edge> edges = new ArrayList<>();
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int n, m;

    static int[][] gods;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());

        gods = new int[n][2];
        parent = new int[n];

        for(int i=0; i<n; i++)
            parent[i] = i;

        for (int i=0; i<n; i++){
            tk = new StringTokenizer(br.readLine());
            gods[i] = new int[] {Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken())};
        }

        for(int i=0; i<m; i++){
            tk = new StringTokenizer(br.readLine());
            union(Integer.parseInt(tk.nextToken())-1, Integer.parseInt(tk.nextToken())-1);
        }

        for(int i=0; i<n-1; i++){
            for (int j=i+1; j<n; j++){
                edges.add(new Edge(i, j, calcDistance(gods[i], gods[j])));
            }
        }

        Collections.sort(edges);

        double total = 0.0;
        for (Edge e: edges){
            if (find(e.to) != find(e.from)){
                total += e.weight;
                union(e.to, e.from);
            }
        }
        total = Math.round(total * 100.0) / 100.0;
        System.out.printf("%.2f", total);
    }

    private static double calcDistance(int[] g1, int[] g2){
        return Math.sqrt(Math.pow(g1[0] - g2[0], 2) + Math.pow(g1[1] - g2[1], 2));
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

class Edge implements Comparable<Edge>{
    int from;
    int to;
    double weight;

    public Edge(int f, int t, double w){
        this.from = f;
        this.to = t;
        this.weight = w;
    }

    @Override
    public int compareTo(Edge edge) {
        if(this.weight - edge.weight > 0)
            return 1;
        else if (this.weight-edge.weight < 0)
            return -1;
        else return 0;
    }

    @Override
    public String toString(){
        return "E[" + from + ", " + to + ", " + weight + "]";
    }
}
