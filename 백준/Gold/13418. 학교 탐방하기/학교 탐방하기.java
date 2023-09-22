import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<Edge> edges = new ArrayList<>();
    static int v, e;

    static int[] parent;

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        v = Integer.parseInt(tk.nextToken());
        e = Integer.parseInt(tk.nextToken());

        for (int i=0; i<=e; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            edges.add(new Edge(from, to, (weight + 1) % 2));
            edges.add(new Edge(to, from, (weight + 1) % 2));
        }

        parent = new int[v+1];
        for (int i=1; i<=v; i++)
            parent[i] = i;
        Collections.sort(edges);
        int sum = 0;
        for (Edge e: edges){
            if (find(e.from) != find(e.to)){
                sum += e.weight;
                union(e.from, e.to);
            }
        }

        Collections.sort(edges, Collections.reverseOrder());
        parent = new int[v+1];
        for (int i=1; i<=v; i++)
            parent[i] = i;
        int rSum = 0;
        for (Edge e: edges){
            if (find(e.from) != find(e.to)){
                rSum += e.weight;
                union(e.from, e.to);
            }
        }

        bw.write((int)(Math.pow(rSum, 2) - Math.pow(sum, 2)) +"");
        bw.flush();
    }

    private static int find(int a){
        if (parent[a] == a)
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

class Edge implements Comparable<Edge>{
    int from;
    int to;
    int weight;

    public Edge(int f, int t, int w){
        this.from = f;
        this.to = t;
        this.weight = w;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }

    @Override
    public String toString(){
        return "E[" + from + ", " + to + ", " + weight + "]";
    }
}