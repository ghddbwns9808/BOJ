import java.io.*;
import java.util.*;

public class Solution {
    List<ArrayList<Integer>> graph, rGraph;
    int N, ans;
    boolean[] visited;
    
    public int solution(int n, int[][] results) {
        N = n;
        graph = new ArrayList<>();
        rGraph = new ArrayList<>();
        
        for (int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
            rGraph.add(new ArrayList<>());
        }
        
        for (int i=0; i<results.length; i++){
            graph.get(results[i][0]).add(results[i][1]);
            rGraph.get(results[i][1]).add(results[i][0]);
        }
        
        for (int i=1; i<=N; i++){
            int n1 = bfs(graph, i);
            int n2 = bfs(rGraph, i);
            if (n1 + n2 == N-1)
                ans++;
        }
        
        return ans;
    }
    
    private int bfs(List<ArrayList<Integer>> g, int start){
        visited = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        int cnt = 0;
        
        while (!q.isEmpty()){
            int cur = q.poll();
            
            for (int nxt: g.get(cur)){
                if (!visited[nxt]){
                    visited[nxt] = true;
                    q.offer(nxt);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}