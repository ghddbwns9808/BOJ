import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, E, pathA, pathB;
    static List<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        input();
        dijkstra();
        int[] distFromA = pathToN(pathA);
        int[] distFromB = pathToN(pathB);

        int ans = Math.min(dist[pathA] + distFromA[pathB] + distFromB[N], dist[pathB] + distFromB[pathA] + distFromA[N]);
        if (dist[pathA] == Integer.MAX_VALUE || dist[pathB] == Integer.MAX_VALUE
        || distFromA[pathB] == Integer.MAX_VALUE || distFromB[pathA] == Integer.MAX_VALUE
        || distFromA[N] == Integer.MAX_VALUE || distFromB[N] == Integer.MAX_VALUE) ans = -1;
        bw.write(ans+"");
        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        E = Integer.parseInt(tk.nextToken());

        visited = new boolean[N+1];
        graph = new ArrayList<>();
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i=0; i<=N; i++)
            graph.add(new ArrayList<>());

        for (int i=0; i<E; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        tk = new StringTokenizer(br.readLine());
        pathA = Integer.parseInt(tk.nextToken());
        pathB = Integer.parseInt(tk.nextToken());
    }

    private static void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[pathA] && visited[pathB]) break;
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

    private static int[] pathToN(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        int[] _dist = new int[N+1];
        Arrays.fill(_dist, Integer.MAX_VALUE);
        _dist[start] = 0;
        visited = new boolean[N+1];

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge e: graph.get(cur.to)){
                if (_dist[e.to] > _dist[cur.to] + e.weight){
                    _dist[e.to] = _dist[cur.to] + e.weight;
                    pq.offer(new Edge(e.to, _dist[e.to]));
                }
            }
        }
        return _dist;
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