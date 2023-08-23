import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[] depth;
    static boolean[] visited;
    static  Vertex[] tree;
    static class Vertex{
        int parent;
        LinkedList<Integer> child;
        public Vertex(int p, LinkedList<Integer> c){
            this.parent = p;
            this.child = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk;
        int n = Integer.parseInt(br.readLine());

        tree = new Vertex[n+1];
        depth = new int[n+1];
        visited = new boolean[n+1];

        for(int i=1; i<=n; i++)
            tree[i] = new Vertex(i, new LinkedList<Integer>());


        for (int i=1; i<n; i++){
            tk = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(tk.nextToken());
            int v2 = Integer.parseInt(tk.nextToken());

            tree[v1].child.add(v2);
            tree[v2].child.add(v1);
        }

        makeDepth();

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(depth[input[0]] >= depth[input[1]])
                System.out.println(lca(input[0], input[1]));
            else
                System.out.println(lca(input[1], input[0]));
        }
    }

    private static void makeDepth(){
        Stack<Integer> s = new Stack<>();
        s.push(1);
        depth[1] = 1;
        visited[1] = true;

        while (!s.isEmpty()){
            int cur = s.pop();

            for (int child: tree[cur].child){
                if(!visited[child]){
                    visited[child] = true;
                    tree[child].parent = cur;
                    depth[child] = depth[cur] + 1;
                    s.push(child);
                }
            }
        }
    }

    private static int lca(int v1, int v2){
        int deeper = v1;
        int upper = v2;

        while (depth[deeper] != depth[upper]){
            deeper = tree[deeper].parent;
        }

        while (deeper != upper){
            deeper = tree[deeper].parent;
            upper = tree[upper].parent;
        }
        return deeper;
    }
}
