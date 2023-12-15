import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, cnt;
    static List<Node> tree;
    static List<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        makeTree(1, 0);
        for (int i=1; i<=N; i++){
            if (tree.get(i).isLeaf)
                cnt += tree.get(i).depth;
        }
        if (cnt%2 == 0) bw.write("No");
        else bw.write("Yes");
        bw.flush();
    }

    private static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        tree = new ArrayList<>();
        visited = new boolean[N+1];

        for (int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
            tree.add(new Node());
        }

        for (int i=0; i<N-1; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
    }

    private static void makeTree(int node, int depth){
        visited[node] = true;
        tree.get(node).depth = depth;
        for (int child: graph.get(node)){
            if (!visited[child]){
                tree.get(node).isLeaf = false;
                makeTree(child, depth+1);
            }
        }
    }

}

class Node{
    boolean isLeaf = true;
    int depth = 0;
}