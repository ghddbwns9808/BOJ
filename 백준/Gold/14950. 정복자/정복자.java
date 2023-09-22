import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int n, m, t;
    static List<ArrayList<Edge>> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());
        t = Integer.parseInt(tk.nextToken());

        for (int i=0; i<=n; i++)
            edges.add(new ArrayList<>());

        for (int i=0; i<m; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            edges.get(from).add(new Edge(to, weight));
            edges.get(to).add(new Edge(from, weight));
        }
        boolean[] visited = new boolean[n+1];
        int cost = 0;
        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()){
            Edge cur = pq.poll();
            if (visited[cur.to])
                continue;

            visited[cur.to] = true;
            cost += cur.weight;

            for (Edge e: edges.get(cur.to)){
                if (!visited[e.to]){
                    pq.add(e);
                }
            }
        }
        bw.write(t * (n-1)*(n-2)/2 + cost + "");
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
}