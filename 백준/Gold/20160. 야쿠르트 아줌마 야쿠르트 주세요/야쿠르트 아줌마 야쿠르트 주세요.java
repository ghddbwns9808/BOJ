import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int V, E, start;
    static int[] path;
    static long[] cost, arrivalTime;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;
    static List<ArrayList<Edge>> graph;
    static Map<Integer, long[]> costs;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        V = Integer.parseInt(tk.nextToken());
        E = Integer.parseInt(tk.nextToken());

        graph = new ArrayList<>();
        path = new int[10];
        costs = new HashMap<>();

        for (int i=0; i<=V; i++)
            graph.add(new ArrayList<>());

        for (int i=0; i<E; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            //양방향
            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        tk = new StringTokenizer(br.readLine());
        for (int i=0; i<10; i++)
            path[i] = Integer.parseInt(tk.nextToken());
        start = Integer.parseInt(br.readLine());

        for(int i=0; i<10; i++){
            //야쿠르트 아줌마가 i 번째 방문하는 지점 기준 다익스트라 가중치가 이미 존재하면 스킵
            if (costs.containsKey(path[i])) continue;
            cost = new long[V+1];
            Arrays.fill(cost, Long.MAX_VALUE);
            pq = new PriorityQueue<>();
            visited = new boolean[V+1];

            pq.offer(new Edge(path[i], 0));
            cost[path[i]] = 0;
            while (!pq.isEmpty()){
                Edge cur = pq.poll();

                if (visited[cur.to]) continue;
                visited[cur.to] = true;

                for (Edge e: graph.get(cur.to)){
                    if(cost[e.to] > cost[cur.to] + e.weight){
                        cost[e.to] = cost[cur.to] + e.weight;
                        pq.offer(new Edge(e.to, cost[e.to]));
                    }
                }
            }
            costs.put(path[i], cost);
        }
        //내 출발지 기준 다익스트라 실행 결과가 없으면 해주기
        if (!costs.containsKey(start)){
            cost = new long[V+1];
            Arrays.fill(cost, Long.MAX_VALUE);
            pq = new PriorityQueue<>();
            visited = new boolean[V+1];

            pq.offer(new Edge(start, 0));
            cost[start] = 0;
            while (!pq.isEmpty()){
                Edge cur = pq.poll();

                if (visited[cur.to]) continue;
                visited[cur.to] = true;

                for (Edge e: graph.get(cur.to)){
                    if(cost[e.to] > cost[cur.to] + e.weight){
                        cost[e.to] = cost[cur.to] + e.weight;
                        pq.offer(new Edge(e.to, cost[e.to]));
                    }
                }
            }
            costs.put(start, cost);
        }

        arrivalTime = new long[10];
        loop:for (int i=1; i<10; i++){
            int prevCity = path[i-1];
            int curCity = path[i];
            long t = costs.get(prevCity)[curCity];

            if (t == Long.MAX_VALUE){
                while (i<10){
                    curCity = path[i];
                    t = costs.get(prevCity)[curCity];
                    arrivalTime[i] = t;
                    if (t != Long.MAX_VALUE)
                        continue loop;
                    i++;
                }
            }else{
                arrivalTime[i] = t;
            }
        }

        Stack<Long> s = new Stack<>();
        s.push(0L);
        for (int i=1; i<10; i++){
            if (arrivalTime[i] != Long.MAX_VALUE){
                arrivalTime[i] = arrivalTime[i] + s.peek();
                s.push(arrivalTime[i]);
            }
        }

        PriorityQueue<Integer> candidates = new PriorityQueue<>();
        for (int i=0; i<10; i++){
            long startToCur = costs.get(start)[path[i]];
            if (arrivalTime[i] != Long.MAX_VALUE && arrivalTime[i] >= startToCur)
                candidates.offer(path[i]);
        }
        int ans = candidates.size() == 0? -1: candidates.poll();
        bw.write(ans+"");
        bw.flush();
    }

    public static void dijkstra(int start){

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
        return (int)(weight - edge.weight);
    }
}