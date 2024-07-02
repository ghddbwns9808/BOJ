import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, K;
    static int[] parents, cost;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        int minCost = solve();
        if (minCost > K) bw.write("Oh no");
        else bw.write(minCost + "");
        bw.flush();
        bw.close();
    }

    static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());

        cost = new int[N+1];

        tk = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++)
            cost[i] = Integer.parseInt(tk.nextToken());

        parents = new int[N+1];
        for (int i=0; i<=N; i++)
            parents[i] = i;
    }

    static int solve() throws IOException {
        while (M --> 0){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            union(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()));
        }
        
        for (int i=0; i<=N; i++)
            find(i);

        Map<Integer, Integer> minCostMap = new HashMap<>();
        for (int i=1; i<=N; i++){
            minCostMap.putIfAbsent(parents[i], cost[i]);
            minCostMap.replace(parents[i], Math.min(cost[i], minCostMap.get(parents[i])));
        }

        int minCost = 0;
        for (int key: minCostMap.keySet())
            minCost += minCostMap.get(key);

        return minCost;
    }

    static void union(int a, int b){
        int A = find(a);
        int B = find(b);

        if (A <= B) parents[B] = A;
        else parents[A] = B;
    }

    static int find(int a){
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
}
