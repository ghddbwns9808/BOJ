import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int R, C;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;
    static List<ArrayList<Edge>> graph;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-->0){
            input();
            bw.write(kruskal() + "\n");
        }
        bw.flush();
    }

    
    private static void input() throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tk.nextToken());
        C = Integer.parseInt(tk.nextToken());

        graph = new ArrayList<>();
        visited = new boolean[R*C + 1];

        for (int i=0; i<=R*C; i++)
            graph.add(new ArrayList<>());

        for (int i=0; i<R; i++){
            tk = new StringTokenizer(br.readLine());
            for (int j=1; j<C; j++){
                int a = C*i + j;
                int b = C*i + j + 1;
                int weight = Integer.parseInt(tk.nextToken());
                graph.get(a).add(new Edge(b, weight));
                graph.get(b).add(new Edge(a, weight));
            }
        }

        for (int i=0; i<R-1; i++){
            tk = new StringTokenizer(br.readLine());
            for (int j=1; j<=C; j++){
                int a = C*i + j;
                int b = C*(i+1) + j;
                int weight = Integer.parseInt(tk.nextToken());
                graph.get(a).add(new Edge(b, weight));
                graph.get(b).add(new Edge(a, weight));
            }
        }
    }

    private static int kruskal(){
        int weightSum = 0;
        pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            weightSum += cur.weight;

            for (Edge e: graph.get(cur.to)){
                pq.offer(e);
            }
        }
        return weightSum;
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