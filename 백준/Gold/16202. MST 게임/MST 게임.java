import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int n, m, k;
    static List<ArrayList<Edge>> edges = new ArrayList<>();
    static int[][] originalEdges;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());
        k = Integer.parseInt(tk.nextToken());

        originalEdges = new int[m][2];

        for (int i=0; i<=n; i++)
            edges.add(new ArrayList<>());

        for (int i=0; i<m; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());

            edges.get(from).add(new Edge(to, i+1));
            edges.get(to).add(new Edge(from, i+1));
            originalEdges[i][0] = from;
            originalEdges[i][1] = to;
        }
        int curTurn = 0;
        while (k-->0){
            boolean canMakeMST = true;
            boolean[] visited = new boolean[n+1];
            int cost = 0;
            pq.add(new Edge(1, 0));
            while (!pq.isEmpty()){
                Edge cur = pq.poll();
                if (visited[cur.to])
                    continue;

                visited[cur.to] = true;
                cost += cur.weight;

                for (Edge e: edges.get(cur.to)){
                    if (!visited[e.to]){
                        pq.add(e);
                    }
                }
            }

            for (int i=1; i<=n; i++){
                if (!visited[i]){
                    canMakeMST = false;
                }
            }

            if (!canMakeMST)
                break;

            int[] rmv = originalEdges[curTurn];
            edges.get(rmv[0]).remove(new Edge(rmv[1], curTurn+1));
            edges.get(rmv[1]).remove(new Edge(rmv[0], curTurn+1));

            curTurn++;
            bw.write(cost + " ");
        }
        k++;
        while (k-->0)
            bw.write("0 ");

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
        return "E[" + ", " + to + ", " + weight + "]";
    }

    @Override
    public boolean equals(Object o) {
        Edge e2 = (Edge) o;
        return (this.to == e2.to && this.weight == e2.weight);
    }
}