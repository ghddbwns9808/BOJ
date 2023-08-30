import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n,m;
    static boolean[] visited;
    static boolean[][] graph;
    static int[] path;
    static Set<String> pathSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        StringTokenizer tk;

        graph = new boolean[n+1][n+1];
        visited = new boolean[n+1];
        path = new int[m];

        for(int i=1; i<=n; i++) {
            tk = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                if(Integer.parseInt(tk.nextToken()) == 1)
                    graph[i][j] = true;
                else
                    graph[i][j] = false;
            }
        }

        tk = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            String p = tk.nextToken();
            path[i] = Integer.parseInt(p);
            pathSet.add(p);
        }

        pathSet.remove(String.valueOf(path[0]));
        dfs(path[0]);

        if(pathSet.isEmpty())
            bw.write("YES");
        else
            bw.write("NO");
        bw.flush();
    }

    private static void dfs(int start) {
        Stack<Integer> s = new Stack<>();
        s.push(start);
        visited[start] = true;

        loop:
        while(!s.isEmpty()) {
            int cur = s.pop();

            for(int i=1; i<=n; i++) {
                if(graph[cur][i] && !visited[i]) {
                    visited[i] = true;
                    s.push(i);
                    pathSet.remove(String.valueOf(i));
                    if(pathSet.isEmpty())
                        break loop;
                }
            }
        }
    }
}