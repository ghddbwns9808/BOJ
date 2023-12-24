import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int T, N, D, C;
    static List<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while (T --> 0){
            input();
            dijkstra();

            int cnt = 0;
            int ans = Integer.MIN_VALUE;

            for (int i=1; i<=N; i++) {
                if (dist[i] != Integer.MAX_VALUE){
                    cnt++;
                    ans = Math.max(ans, dist[i]);
                }
            }

            bw.write(cnt + " " + ans + "\n");
        }

        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        D = Integer.parseInt(tk.nextToken());
        C = Integer.parseInt(tk.nextToken());

        dist = new int[N+1];
        visited = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        graph = new ArrayList<>();
        for (int i=0; i<=N; i++)
            graph.add(new ArrayList<>());

        for (int i=0; i<D; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            graph.get(to).add(new Edge(from, weight));
        }
    }

    private static void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(C, 0));
        dist[C] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();
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