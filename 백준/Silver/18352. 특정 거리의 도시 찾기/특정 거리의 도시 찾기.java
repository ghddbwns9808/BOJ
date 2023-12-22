import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, K, X;
    static List<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        input();
        dijkstra();

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i=1; i<=N; i++){
            if (dist[i] == K)
                ans.add(i);
        }
        Collections.sort(ans);
        if (ans.isEmpty())
            bw.write("-1");
        else{
            for (int n : ans)
                bw.write(n+"\n");
        }

        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());
        X = Integer.parseInt(tk.nextToken());

        dist = new int[N+1];
        visited = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        graph = new ArrayList<>();
        for (int i=0; i<=N; i++)
            graph.add(new ArrayList<>());

        for (int i=0; i<M; i++){
            tk = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(tk.nextToken())).add(new Edge(Integer.parseInt(tk.nextToken()), 1));
        }
    }

    private static void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(X, 0));
        dist[X] = 0;

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