import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int n, c;
    static List<ArrayList<Edge>> edges = new ArrayList<>();
    static int[][] originalEdges;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        c = Integer.parseInt(tk.nextToken());
        originalEdges = new int[n][2];
        for (int i=0; i<=n; i++)
            edges.add(new ArrayList<>());

        for (int i=0; i<n; i++){
            tk = new StringTokenizer(br.readLine());
            originalEdges[i] = new int[]{Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken())};
        }

        for (int i=0; i<n-1; i++){
            for (int j=i+1; j<n; j++){
                int d = calcDist(originalEdges[i],originalEdges[j] );
                if (d >= c){
                    edges.get(i).add(new Edge(j, d));
                    edges.get(j).add(new Edge(i, d));
                }
            }

        }

        boolean[] visited = new boolean[n];
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
        boolean canMakeMst = true;
        for (boolean b: visited){
            if (!b)
                canMakeMst = false;
        }
        if (canMakeMst) bw.write(cost +"");
        else bw.write("-1");
        bw.flush();
    }
    private static int calcDist(int[] p1, int[] p2){
        return (int)(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
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