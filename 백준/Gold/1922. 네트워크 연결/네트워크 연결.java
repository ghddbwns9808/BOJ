import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<ArrayList<Edge>> edges = new ArrayList<>();
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[][] planets;
    static int n, m;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[n+1];

        for(int i=0; i <= n; i++)
            edges.add(new ArrayList<>());

        for (int i=0; i<m; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            edges.get(from).add(new Edge(to, weight));
            edges.get(to).add(new Edge(from, weight));
        }

        pq.offer(new Edge(1, 0));

        int total = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();
            if(visited[cur.to]) continue;

            visited[cur.to] = true;
            total += cur.weight;

            for (Edge e: edges.get(cur.to)){
                if (!visited[e.to])
                    pq.add(e);
            }
        }

        bw.write(total + "");
        bw.flush();
    }

    private static int calcDist(int[] star1, int[] star2){
        int d1 = Math.abs(star1[0] - star2[0]);
        int d2 = Math.abs(star1[1] - star2[1]);
        int d3 = Math.abs(star1[2] - star2[2]);
        return Math.min(Math.min(d1, d2), d3);
    }
}

class Edge implements Comparable<Edge>{
    int to;
    int weight;

    public Edge( int t, int w){
        this.to = t;
        this.weight = w;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }

    @Override
    public String toString(){
        return "E[" + to + ", " + weight + "]";
    }
}
