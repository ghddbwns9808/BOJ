import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static List<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        input();
        bw.write(bfs()+"");
        bw.flush();
    }

    private static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        graph = new ArrayList<>();
        for (int i=0; i<=N; i++)
            graph.add(new ArrayList<>());

        int pair = Integer.parseInt(br.readLine());
        while (pair-- > 0){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
    }

    private static int bfs(){
        int cnt = 0;
        visited[1] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        while (!q.isEmpty()){
            int cur = q.poll();

            for (int n: graph.get(cur)){
                if (!visited[n]){
                    q.offer(n);
                    visited[n] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }
}