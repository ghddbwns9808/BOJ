import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int V, E, start;
    static int[] cost;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;
    static List<ArrayList<Edge>> graph;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        V = Integer.parseInt(tk.nextToken());
        E = Integer.parseInt(tk.nextToken());

        start = Integer.parseInt(br.readLine());
        cost = new int[V+1];
        visited = new boolean[V+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        graph = new ArrayList<>();
        pq = new PriorityQueue<>();

        for (int i=0; i<=V; i++)
            graph.add(new ArrayList<>());

        for (int i=0; i<E; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            graph.get(from).add(new Edge(to, weight));
        }

        pq.offer(new Edge(start, 0));
        cost[start] = 0;
        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge e: graph.get(cur.to)){
                if(cost[e.to] > cost[cur.to] + e.weight){
                    cost[e.to] = cost[cur.to] + e.weight;
                    pq.offer(new Edge(e.to, cost[e.to]));
                }
            }
        }

        for (int i=1; i<=V; i++) {
            if (cost[i] != Integer.MAX_VALUE) bw.write(cost[i] + "\n");
            else bw.write("INF\n");
        }
        bw.flush();
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