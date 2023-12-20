import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, departure, arrival;
    static List<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        input();
        dijkstra();

        bw.write(dist[arrival]+"");
        bw.flush();
    }

    private static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N+1];
        graph = new ArrayList<>();
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i=0; i<=N; i++)
            graph.add(new ArrayList<>());

        StringTokenizer tk;
        for (int i=0; i<M; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            graph.get(from).add(new Edge(to, weight));
        }

        tk = new StringTokenizer(br.readLine());
        departure = Integer.parseInt(tk.nextToken());
        arrival = Integer.parseInt(tk.nextToken());
    }

    private static void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(departure, 0));
        dist[departure] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[arrival]) break;
            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge e: graph.get(cur.to)){
                if (dist[e.to] > dist[cur.to] + e.weight){
                    dist[e.to] = dist[cur.to] + e.weight;
                    pq.offer(new Edge(e.to, dist[e.to]));
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