import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[][] dp;
    static boolean[] visited;
    static List<ArrayList<Integer>> tree;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        dp = new int[N+1][2];
        tree = new ArrayList<>();

        for (int i=0; i<=N; i++)
            tree.add(new ArrayList<>());

        for (int i=1; i<N; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        dp(1);

        bw.write(Math.min(dp[1][0], dp[1][1]) + "");


        bw.flush();
    }

    static void dp(int cur){
        visited[cur] = true;
        dp[cur][0] = 1;

        for (int c: tree.get(cur)){
            if (!visited[c]){
                dp(c);
                dp[cur][0] += Math.min(dp[c][0], dp[c][1]);
                dp[cur][1] += dp[c][0];
            }
        }
    }
}
/*
1. 어떤 노드에 대해 내가 얼리어답터거나, 얼리어답터가 아니거나 둘 중 하나임
2. dp[cur][0] -> cur 노드가 얼답일 때 최소 얼리어답터의 수
3. dp[cur][1] -> cur 노드가 얼답이 아닐 때 최소 얼답의 수
4. dp[cur][1] -> 차일드 노드가 모두 얼답이어야 함
-> sumOf(dp[child][0])
5. dp[cur][0] -> 차일드 노드가 얼답이어도 되고 얼답이 아니어도 됨 -> 더 작은 값으로 가져오면 됨
-> sumOf(min(dp[child][0], dp[child][1])
 */