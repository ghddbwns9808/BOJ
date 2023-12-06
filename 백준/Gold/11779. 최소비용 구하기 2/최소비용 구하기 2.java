import org.w3c.dom.xpath.XPathResult;

import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, start, end;
    static List<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int[] cost, route;

    public static void main(String[] args) throws IOException {
        input();
        solve();

        Stack<Integer> s= new Stack<>();
        s.push(end);
        int cur = end;
        while (cur != start){
            s.push(route[cur]);
            cur = route[cur];
        }
        bw.write(cost[end]+"\n");
        bw.write(s.size()+"\n");
        while (!s.isEmpty())
            bw.write(s.pop()+" ");

        bw.flush();
    }

    private static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i=0; i<=N; i++)
            graph.add(new ArrayList<>());

        cost = new int[N+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        route = new int[N+1];
        visited = new boolean[N+1];

        while (M-->0){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            graph.get(from).add(new Edge(to, weight));
        }

        StringTokenizer tk = new StringTokenizer(br.readLine());
        start = Integer.parseInt(tk.nextToken());
        end = Integer.parseInt(tk.nextToken());
    }

    private static void solve(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        cost[start] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (cur.to == end) break;
            if (visited[cur.to]) continue;
            visited[cur.to] = true;

            for (Edge e: graph.get(cur.to)){
                if (cost[cur.to] + e.weight < cost[e.to]){
                    cost[e.to] = cost[cur.to] + e.weight;
                    route[e.to] = cur.to;
                    pq.offer(new Edge(e.to, cost[e.to]));
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