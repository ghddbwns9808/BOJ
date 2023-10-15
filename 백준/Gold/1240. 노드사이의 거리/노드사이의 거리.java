import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] adjacent;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        adjacent = new int[N+1][N+1];

        for (int i=0; i<N-1; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int cost = Integer.parseInt(tk.nextToken());

            adjacent[from][to] = cost;
            adjacent[to][from] = cost;
        }

        for (int i=0; i<M; i++){
            tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());

            bw.write(bfs(from, to) + "\n");
        }
        bw.flush();
    }

    private static int bfs(int from, int to){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{from, 0});
        boolean[] visited = new boolean[N+1];
        visited[from] = true;

        while (!q.isEmpty()){
            int[] cur = q.poll();

            if (cur[0] == to)
                return cur[1];

            for (int i=1; i<N+1; i++){
                if (adjacent[cur[0]][i] != 0 && !visited[i]){
                    q.add(new int[]{i, cur[1] + adjacent[cur[0]][i]});
                    visited[i] = true;
                }
            }
        }
        return -1;
    }
}

class Node{
    int to;
    int cost;

    public Node(int to, int c){
        this.to = to;
        this.cost = c;
    }
}