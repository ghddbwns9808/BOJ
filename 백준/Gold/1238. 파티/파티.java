import org.w3c.dom.xpath.XPathResult;

import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, X;
    static List<ArrayList<Edge>> graph, rGraph;
    static boolean[] visited;
    static int[] cost, rCost;

    public static void main(String[] args) throws IOException {
        input();

        dijkstra();
        rDijkstra();

        for (int i=1; i<=N; i++)
            cost[i] += rCost[i];

        Arrays.sort(cost);

        bw.write(cost[N-1]+"");
        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        X = Integer.parseInt(tk.nextToken());

        cost = new int[N+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        rCost = new int[N+1];
        Arrays.fill(rCost, Integer.MAX_VALUE);

        graph = new ArrayList<>();
        rGraph = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
            rGraph.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            graph.get(from).add(new Edge(to, weight));
            rGraph.get(to).add(new Edge(from, weight));
        }
    }

    private static void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(X, 0));
        visited = new boolean[N+1];
        cost[X] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge e: graph.get(cur.to)){
                if (cost[cur.to] + e.weight < cost[e.to]){
                    cost[e.to] = cost[cur.to] + e.weight;
                    pq.offer(new Edge(e.to, cost[e.to]));
                }
            }
        }
    }

    private static void rDijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(X, 0));
        visited = new boolean[N+1];
        rCost[X] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge e: rGraph.get(cur.to)){
                if (rCost[cur.to] + e.weight < rCost[e.to]){
                    rCost[e.to] = rCost[cur.to] + e.weight;
                    pq.offer(new Edge(e.to, rCost[e.to]));
                }
            }
        }
    }


}

class Edge implements Comparable<Edge>{
    int to, weight;

    public Edge(int t, int w){
        this.to = t;
        this.weight = w;
    }


    @Override
    public int compareTo(Edge edge) {
        return weight - edge.weight;
    }
}