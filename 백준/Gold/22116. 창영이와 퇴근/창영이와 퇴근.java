import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static boolean[] visited;
    static int[][] map;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] parent;
    static PriorityQueue<Edge> pq;
    static List<ArrayList<Edge>> graph;
    public static void main(String[] args) throws IOException {
        input();
        bw.write(kruskal()+"");
        bw.flush();
    }

    
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        visited = new boolean[N*N];
        map = new int[N][N];
        parent = new int[N*N];
        for (int i=0; i<N*N; i++)
            parent[i] = i;

        for (int i=0; i<N*N; i++)
            graph.add(new ArrayList<>());

        for (int i=0; i<N; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++)
                map[i][j] = Integer.parseInt(tk.nextToken());
        }

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                for (int k=0; k<4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (isValidPosition(nx, ny)){
                        graph.get(N*i + j).add(new Edge(N*nx + ny, Math.abs(map[i][j] - map[nx][ny])));
                    }
                }
            }
        }
    }

    static boolean isValidPosition(int x, int y){
        return 0<=x && x<N && 0<=y && y<N;
    }

    private static int dijkstra(){
        int[] cost = new int[N*N];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;
        pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge e: graph.get(cur.to)){
                if (cost[e.to] > cost[cur.to] + e.weight){
                    cost[e.to] = cost[cur.to] + e.weight;
                    pq.offer(new Edge(e.to, cost[e.to]));
                }
            }
        }
        return cost[N*N-1];
    }

    private static int kruskal(){
        pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        int maxDiff = Integer.MIN_VALUE;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            maxDiff = Math.max(maxDiff, cur.weight);
            union(0, cur.to);
            if (parent[0] == parent[N*N-1])
                return maxDiff;

            for (Edge e: graph.get(cur.to)){
                pq.offer(e);
            }
        }
        return -1;
    }

    static int find(int a){
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);

    }

    static void union(int a, int b){
        int A = find(a);
        int B = find(b);
        if (A!=B)
            parent[B] = A;
    }

}

class Edge implements Comparable<Edge>{
    int to, weight;

    public Edge(int t, int w){
        to = t;
        weight = w;
    }

    @Override
    public int compareTo(Edge edge) {
        return weight - edge.weight;
    }

    @Override
    public String toString() {
        return "Edge[ to: " + to + ", weight: " + weight + "]";
    }
}