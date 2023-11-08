import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, K, S, P, Q;
    static boolean[] visited;
    static int[] occupied, initialOccupied;
    static long[] cost;
    static PriorityQueue<Edge> pq;
    static List<ArrayList<Edge>> graph;
    static HashSet<Integer> initOccupied;
    public static void main(String[] args) throws IOException {
        input();

        for (int n: initialOccupied)
            bfs(n);

        findPath();

        bw.write((cost[N] - occupied[N]) + "");
        bw.flush();
    }

    private static void input() throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M =Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());
        S = Integer.parseInt(tk.nextToken());

        tk = new StringTokenizer(br.readLine());
        P = Integer.parseInt(tk.nextToken());
        Q = Integer.parseInt(tk.nextToken());

        initOccupied = new HashSet<>();

        occupied = new int[N+1];
        Arrays.fill(occupied, P);

        initialOccupied = new int[K];
        for (int i=0; i<K; i++) {
            initialOccupied[i] = Integer.parseInt(br.readLine());
            initOccupied.add(initialOccupied[i]);
        }

        graph = new ArrayList<>();
        for (int i=0; i<=N; i++)
            graph.add(new ArrayList<>());

        for (int i=0; i<M; i++){
            tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            graph.get(a).add(new Edge(b, 0));
            graph.get(b).add(new Edge(a, 0));
        }
    }

    private static void bfs(int start){
        Deque<Edge> q = new ArrayDeque<>();
        q.offer(new Edge(start, 0));
        visited = new boolean[N+1];
        visited[start] = true;
        occupied[start] = Q;

        while (!q.isEmpty()){
            Edge cur = q.pollFirst();

            for (Edge e: graph.get(cur.to)){
                if (!visited[e.to] && cur.weight< S){
                    visited[e.to] = true;
                    occupied[e.to] = Q;
                    q.offer(new Edge(e.to, cur.weight+1));
                }

            }
        }
    }

    private static void findPath(){
        pq = new PriorityQueue<>();
        visited = new boolean[N+1];
        cost = new long[N+1];
        Arrays.fill(cost, Long.MAX_VALUE);
        cost[1] = 0;
        pq.offer(new Edge(1, 0));

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge e: graph.get(cur.to)){
                if (initOccupied.contains(e.to)) continue;
                if (cost[e.to] > cost[cur.to] + occupied[e.to]){
                    cost[e.to] = cost[cur.to] + occupied[e.to];
                    pq.offer(new Edge(e.to, cost[e.to]));
                }
            }
        }
    }
}

class Edge implements Comparable<Edge>{
    int to;
    long weight;
    public Edge(int t, long w){
        this.to = t;
        this.weight = w;
    }

    @Override
    public int compareTo(Edge edge) {
        return (int)(weight - edge.weight);
    }
}