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

        // 인접 노드끼리 거리 정보를 2차원 int 배열로 저장
        // 인접 리스트로 저장하는 것이 더 효율적일 듯?
        //그치만 귀찮은걸...
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

            //from에서 to 가는 거리 bfs로 찾기
            bw.write(bfs(from, to) + "\n");
        }
        bw.flush();
    }

    private static int bfs(int from, int to){
        //int[2] -> {이번 노드 번호, 지금까지 더해진 거리}
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{from, 0});
        boolean[] visited = new boolean[N+1];
        visited[from] = true;

        while (!q.isEmpty()){
            int[] cur = q.poll();

            //이번에 꺼낸 노드 번호가 to 면 지금까지 더한 거리 return
            if (cur[0] == to)
                return cur[1];

            for (int i=1; i<N+1; i++){
                if (adjacent[cur[0]][i] != 0 && !visited[i]){
                    q.add(new int[]{i, cur[1] + adjacent[cur[0]][i]});
                    visited[i] = true;
                }
            }
        }
        //안 이어지는 경우는 없는 듯?
        return -1;
    }
}

/*
1. 인접한 노드끼리의 거리를 2차원 int 배열에 저장
2. bfs로 두 노드 사이의 거리 찾기
 */