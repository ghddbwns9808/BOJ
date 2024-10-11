import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, J, S;
    static int[] jiheon, sungha;
    static boolean[] vst;
    static List<ArrayList<Edge>> graph;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        input();
        solve();

        bw.flush();
    }

    private static void input() throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        jiheon = new int[N+1];
        sungha = new int[N+1];
        vst = new boolean[N+1];

        graph = new ArrayList<>();
        for (int i=0; i<=N; i++)
            graph.add(new ArrayList());

        while (M-->0){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        tk = new StringTokenizer(br.readLine());
        J = Integer.parseInt(tk.nextToken());
        S = Integer.parseInt(tk.nextToken());
    }

    private static void solve() throws IOException {
        dijkstra();

        List<Integer> c3Candidates = condition3();

        List<Integer> c4Candidates = new ArrayList<>();
        for (int n: c3Candidates){
            if (sungha[n] < jiheon[n])
                continue;
            c4Candidates.add(n);
        }

        if (c4Candidates.size() == 1){
            bw.write(c4Candidates.get(0) + "");
            return;
        }

        if (c4Candidates.size() == 0){
            bw.write("-1");
            return;
        }


        PriorityQueue<Integer> lastCandidates = new PriorityQueue<>();
        int min = Integer.MAX_VALUE;
        for (int n: c4Candidates){
            if (min == jiheon[n]){
                lastCandidates.offer(n);
            }else if (min > jiheon[n]){
                lastCandidates.clear();
                lastCandidates.offer(n);
                min = jiheon[n];
            }
        }

        bw.write(lastCandidates.poll() + "");
    }


    private static List<Integer> condition3(){
        int[] sum = new int[N+1];
        for (int i=1; i<=N; i++)
            sum[i] = jiheon[i] + sungha[i];

        int min = Integer.MAX_VALUE;
        List<Integer> candidates = new ArrayList<>();
        for (int i=1; i<=N; i++){
            if (i == J || i == S) continue;
            if (min == sum[i]){
                candidates.add(i);
            }else if (min > sum[i]){
                candidates.clear();
                candidates.add(i);
                min = sum[i];
            }
        }

        return candidates;
    }

    private static void dijkstra(){
        pq = new PriorityQueue<>();
        pq.offer(new Edge(J, 0));
        Arrays.fill(jiheon, Integer.MAX_VALUE);
        jiheon[J] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (vst[cur.to]) continue;
            vst[cur.to] = true;

            for (Edge e: graph.get(cur.to)){
                if (jiheon[e.to] > jiheon[cur.to] + e.weight){
                    jiheon[e.to] = jiheon[cur.to] + e.weight;
                    pq.offer(new Edge(e.to, jiheon[e.to]));
                }
            }
        }

        pq = new PriorityQueue<>();
        pq.offer(new Edge(S, 0));
        vst = new boolean[N+1];
        Arrays.fill(sungha, Integer.MAX_VALUE);
        sungha[S] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (vst[cur.to]) continue;
            vst[cur.to] = true;

            for (Edge e: graph.get(cur.to)){
                if (sungha[e.to] > sungha[cur.to] + e.weight){
                    sungha[e.to] = sungha[cur.to] + e.weight;
                    pq.offer(new Edge(e.to, sungha[e.to]));
                }
            }
        }
    }
}


class Edge implements Comparable<Edge> {
    int to, weight;

    public Edge(int t, int w){
        to = t;
        weight = w;
    }


    @Override
    public int compareTo(Edge edge) {
        return weight - edge.weight;
    }
}
