import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, curSccNum;
    static List<PriorityQueue<Integer>> graph, rGraph;
    static boolean[] visited;
    static int[] sccArr, indegree;
    static Stack<Integer> s;
    static List<int[]> edges;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T --> 0){
            //region 전역 변수 초기화
            StringTokenizer tk = new StringTokenizer(br.readLine());
            N = Integer.parseInt(tk.nextToken());
            M = Integer.parseInt(tk.nextToken());
            graph = new ArrayList<>();
            rGraph = new ArrayList<>();
            edges = new ArrayList<>();
            s = new Stack<>();
            visited = new boolean[N+1];
            sccArr = new int[N+1];

            for (int i=0; i<=N; i++){
                graph.add(new PriorityQueue<>());
                rGraph.add(new PriorityQueue<>());
            }
            //endregion

            //region 입력
            for (int i=0; i<M; i++){
                tk = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(tk.nextToken());
                int to = Integer.parseInt(tk.nextToken());

                edges.add(new int[]{from, to});
                graph.get(from).offer(to);
                rGraph.get(to).offer(from);
            }
            //endregion

            for (int i=1; i<=N; i++){
                if (!visited[i]){
                    visited[i] = true;
                    dfs(i);
                }
            }
            visited = new boolean[N+1];
            curSccNum = 0;
            while (!s.isEmpty()){
                int cur = s.pop();
                if (!visited[cur]){
                    visited[cur] = true;
                    rDfs(cur);
                    curSccNum++;
                }
            }

            indegree = new int[curSccNum];
            for (int[] edge: edges){
                if (sccArr[edge[0]] != sccArr[edge[1]]){
                    indegree[sccArr[edge[1]]]++;
                }
            }

            int cnt = 0;
            for (int i=0; i< indegree.length; i++){
                if (indegree[i] == 0)
                    cnt++;
            }

            bw.write(cnt+"\n");
            bw.flush();
        }
    }

    private static void dfs(int cur){
        PriorityQueue<Integer> child = graph.get(cur);
        while (!child.isEmpty()){
            int c = child.poll();
            if (!visited[c]){
                visited[c] = true;
                dfs(c);
            }
        }
        s.push(cur);
    }

    private static void rDfs(int cur){
        sccArr[cur] = curSccNum;
        PriorityQueue<Integer> child = rGraph.get(cur);
        while (!child.isEmpty()){
            int c = child.poll();
            if (!visited[c]){
                visited[c] = true;
                sccArr[cur] = curSccNum;
                rDfs(c);
            }
        }
    }
}