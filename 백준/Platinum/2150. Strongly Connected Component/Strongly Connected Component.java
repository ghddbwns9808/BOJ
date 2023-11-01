import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int V, E;
    //진입차수
    static List<PriorityQueue<Integer>> graph, rGraph;
    static boolean[] visited;
    static Stack<Integer> s;
    static List<PriorityQueue<Integer>> sccArr;
    static PriorityQueue<Integer> curScc;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        V = Integer.parseInt(tk.nextToken());
        E = Integer.parseInt(tk.nextToken());

        visited = new boolean[V+1];
        graph = new ArrayList<>();
        rGraph = new ArrayList<>();
        sccArr = new ArrayList<>();
        s = new Stack<>();

        for (int i=0; i<=V; i++){
            graph.add(new PriorityQueue<>());
            rGraph.add(new PriorityQueue<>());
        }

        for (int i=0; i<E; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());

            graph.get(from).offer(to);
            rGraph.get(to).offer(from);
        }

        for (int i=1; i<=V; i++){
            if (!visited[i]){
                visited[i] = true;
                dfs(i);
            }
        }

        visited = new boolean[V+1];
        while (!s.isEmpty()){
            int cur = s.pop();
            if (!visited[cur]){
                curScc = new PriorityQueue<>();
                visited[cur] = true;
                rDfs(cur);
                sccArr.add(curScc);
            }
        }

        Collections.sort(sccArr, new Comparator<PriorityQueue<Integer>>() {
            @Override
            public int compare(PriorityQueue<Integer> integers, PriorityQueue<Integer> t1) {
                return integers.peek() - t1.peek();
            }
        });

        bw.write(sccArr.size()+"\n");
        for (PriorityQueue<Integer> scc: sccArr){
            while (!scc.isEmpty())
                bw.write(scc.poll() + " ");
            bw.write("-1\n");
        }

        bw.flush();
    }

    static private void dfs(int cur){
        PriorityQueue<Integer> children = graph.get(cur);
        while (!children.isEmpty()){
            int n = children.poll();
            if (!visited[n]){
                visited[n] = true;
                dfs(n);
            }
        }
        s.push(cur);
    }

    static private void rDfs(int cur){
        PriorityQueue<Integer> children = rGraph.get(cur);
        while (!children.isEmpty()){
            int n = children.poll();
            if (!visited[n]){
                visited[n] = true;
                rDfs(n);
            }
        }
        curScc.offer(cur);
    }
}