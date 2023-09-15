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
    static int[][] stars;
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        stars = new int[n][2];
        boolean[] visited = new boolean[n];

        for (int i=0; i<n; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());

            stars[i][0] = (int) (100 * Double.parseDouble(tk.nextToken()));
            stars[i][1] = (int) (100 * Double.parseDouble(tk.nextToken()));
        }

        for(int i=0; i < n; i++)
            edges.add(new ArrayList<>());

        for (int i=0; i<n-1; i++){
            for (int j=i; j<n; j++){
                int d = calcDist(stars[i], stars[j]);
                edges.get(i).add(new Edge(j, d));
                edges.get(j).add(new Edge(i, d));
            }
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

        bw.write(((double)total / 100.0) + "");
        bw.flush();
    }

    private static int calcDist(int[] star1, int[] star2){
        return (int) Math.sqrt(Math.pow((star1[0] - star2[0]), 2) + Math.pow((star1[1] - star2[1]), 2));
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
}