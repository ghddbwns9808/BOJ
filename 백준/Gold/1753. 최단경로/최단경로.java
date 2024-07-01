import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static List<ArrayList<Edge>> relations;
    static int[] ans;
    static boolean[] vst;
    static int V, E, start;

    public static void main(String[] args) throws IOException {
        input();
        solve();

        for (int i=1; i<=V; i++){
            if (ans[i] == Integer.MAX_VALUE) bw.write("INF\n");
            else bw.write(ans[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        V = Integer.parseInt(tk.nextToken());
        E = Integer.parseInt(tk.nextToken());
        start = Integer.parseInt(br.readLine());

        relations = new ArrayList<>();
        vst = new boolean[V+1];
        ans = new int[V+1];
        Arrays.fill(ans, Integer.MAX_VALUE);

        for (int i=0; i<=V; i++)
            relations.add(new ArrayList<>());

        for (int i=0; i<E; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            relations.get(from).add(new Edge(to, weight));
        }

    }

    static void solve(){
        PriorityQueue<Edge> pq = new PriorityQueue();
        pq.offer(new Edge(start, 0));
        ans[start] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (vst[cur.to]) continue;
            vst[cur.to] = true;

            for (Edge nxt: relations.get(cur.to)){
                if (ans[nxt.to] > ans[cur.to] + nxt.weight){
                    pq.offer(new Edge(nxt.to, ans[cur.to] + nxt.weight));
                    ans[nxt.to] = ans[cur.to] + nxt.weight;
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
    public int compareTo(Edge other){
        return this.weight - other.weight;
    }
}