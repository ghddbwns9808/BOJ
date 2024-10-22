import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, K, city;
    static long distance;
    static long[] dist, minDist;
    static Set<Integer> interview;
    static boolean[] vst;
    static List<ArrayList<Edge>> graph;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        for (int i=1; i<=N; i++){
            if (interview.contains(i)) continue;
            if (distance < dist[i]){
                distance = dist[i];
                city = i;
            }
        }
        bw.write(city + "\n" + distance);
        bw.flush();
    }

    private static void input() throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());
        distance = Long.MIN_VALUE;

        interview = new HashSet<>();
        graph = new ArrayList<>();

        for (int i=0; i<=N; i++)
            graph.add(new ArrayList<>());

        while (M-->0){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());
            graph.get(to).add(new Edge(from, weight));
        }

        tk = new StringTokenizer(br.readLine());
        for (int i=0; i<K; i++)
            interview.add(Integer.parseInt(tk.nextToken()));
    }

    private static void solve () {
        dist = new long[N+1];
        vst = new boolean[N+1];

        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i: interview){
            dist[i] = 0;
            pq.offer(new Edge(i, 0));
        }

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (vst[cur.to]) continue;
            vst[cur.to] = true;

            for (Edge nxt: graph.get(cur.to)){
                if (dist[nxt.to] > dist[cur.to] + nxt.weight){
                    dist[nxt.to] = dist[cur.to] + nxt.weight;
                    pq.offer(new Edge(nxt.to, dist[nxt.to]));
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
        return (int) (weight - edge.weight);
    }
}