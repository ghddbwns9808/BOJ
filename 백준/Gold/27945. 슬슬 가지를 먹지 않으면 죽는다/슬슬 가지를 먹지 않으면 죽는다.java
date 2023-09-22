import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int n, m;
    static List<ArrayList<Edge>> edges = new ArrayList<>();
    static int[][] originalEdges;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());

        for (int i=0; i<=n; i++)
            edges.add(new ArrayList<>());

        for (int i=1; i<=m; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            edges.get(from).add(new Edge(to, weight));
            edges.get(to).add(new Edge(from, weight));
        }

        boolean[] visited = new boolean[n+1];
        boolean[] vst = new boolean[1000000001];
        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()){
            Edge cur = pq.poll();
            if (visited[cur.to])
                continue;

            visited[cur.to] = true;
            vst[cur.weight] = true;

            for (Edge e: edges.get(cur.to)){
                if (!visited[e.to]){
                    pq.add(e);
                }
            }
        }
        int i;
        for (i=1; i<vst.length; i++){
            if (!vst[i])
                break;
        }
        bw.write(i+"");
        bw.flush();
    }
}

class Edge implements Comparable<Edge>{
    int to;
    int weight;

    public Edge( int t, int w){
        this.to = t;
        this.weight = w;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }

    @Override
    public String toString(){
        return "E[" + ", " + to + ", " + weight + "]";
    }

    @Override
    public boolean equals(Object o) {
        Edge e2 = (Edge) o;
        return (this.to == e2.to && this.weight == e2.weight);
    }
}