import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, tc;
    static List<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int[] dist;
    static int[][] map;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        while (true){
            input();
            if (N == 0) break;
            dijkstra();
            bw.write("Problem " + tc + ": " + (dist[N*N-1] + map[0][0]) +  "\n");
        }
        bw.flush();
    }

    private static void input() throws IOException{
        tc++;
        N = Integer.parseInt(br.readLine());
        if (N == 0) return;

        map = new int[N][N];
        visited = new boolean[N*N];
        graph = new ArrayList<>();
        dist = new int[N*N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i=0; i<N*N; i++)
            graph.add(new ArrayList<>());

        StringTokenizer tk;
        for (int i=0; i<N; i++){
            tk = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++)
                map[i][j] = Integer.parseInt(tk.nextToken());
        }

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                for (int k=0; k<4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (isValidPosition(nx, ny)){
                        graph.get(N*i + j).add(new Edge(N*nx+ny, map[nx][ny]));
                    }
                }
            }
        }

    }

    private static boolean isValidPosition(int x, int y){
        return x >= 0 && x <N && y >= 0 && y < N;
    }

    private static void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));
        dist[0] = 0;

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