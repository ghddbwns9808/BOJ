import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, R, Q, cnt;
    static List<ArrayList<Integer>> graph;
    static List<Node> tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        makeTree();
        countSubTree(R);
        solve();

        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        R = Integer.parseInt(tk.nextToken());
        Q = Integer.parseInt(tk.nextToken());

        visited = new boolean[N+1];
        graph = new ArrayList<>();
        tree = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
            tree.add(new Node());
        }
        for (int i=0; i<N-1; i++){
            tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
    }

    private static void makeTree(){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(R);
        visited[R] = true;

        while (!q.isEmpty()){
            int cur = q.poll();

            for (int child: graph.get(cur)){
                if (!visited[child]){
                    tree.get(cur).children.add(child);
                    q.add(child);
                    visited[child] = true;
                }
            }
        }
    }

    private static void countSubTree(int node){
        for (int child: tree.get(node).children){
            countSubTree(child);
            tree.get(node).subTreeCnt += tree.get(child).subTreeCnt;
        }
    }

    private static void solve() throws IOException {
        while (Q-->0){
            int root = Integer.parseInt(br.readLine());
            bw.write(tree.get(root).subTreeCnt+"\n");
        }
    }
}

class Node{
    int n;
    List<Integer> children = new ArrayList<>();
    int subTreeCnt = 1;

}
