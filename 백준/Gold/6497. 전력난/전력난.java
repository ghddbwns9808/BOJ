import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<ArrayList<Edge>> edges;
    static PriorityQueue<Edge> pq;
    static int n, m;

    public static void main(String[] args) throws IOException {
        while (true){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            n = Integer.parseInt(tk.nextToken());
            m = Integer.parseInt(tk.nextToken());

            if (n==0 && m ==0)
                break;

            edges = new ArrayList<>();
            pq = new PriorityQueue<>();

            boolean[] visited = new boolean[n];

            for(int i=0; i < n; i++)
                edges.add(new ArrayList<>());

            int totalWeight = 0;

            for (int i=0; i<m; i++){
                tk = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(tk.nextToken());
                int to = Integer.parseInt(tk.nextToken());
                int weight = Integer.parseInt(tk.nextToken());
                totalWeight += weight;

                edges.get(from).add(new Edge(to, weight));
                edges.get(to).add(new Edge(from, weight));
            }

            pq.offer(new Edge(0, 0));

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
            bw.write((totalWeight - total) + "\n");
        }
        bw.flush();
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
