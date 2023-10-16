import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] parent;
    static List<Node> tree;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T --> 0){
            int tmpSum = 0;
            N = Integer.parseInt(br.readLine());
            parent = new int[N+1];
            tree = new ArrayList<>();

            for (int i=0; i<=N; i++)
                tree.add(new Node(i));

            for(int i=1; i<N; i++){
                StringTokenizer tk = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(tk.nextToken());
                int c = Integer.parseInt(tk.nextToken());
                tmpSum += c;

                tree.get(p).children.add(c);
                tree.get(c).parent = p;
            }

            int sum = N%2 == 0 ? (1 + N) * (N/2) : (1 + N) * (N/2) + (N/2)+1;
            int root = sum - tmpSum;

            setDepth(root, 0);

            StringTokenizer tk = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(tk.nextToken());
            int n2 = Integer.parseInt(tk.nextToken());

            if (tree.get(n1).depth >= tree.get(n2).depth) bw.write(lca(n1, n2) + "\n");
            else bw.write(lca(n2, n1) + "\n");
        }
        bw.flush();
    }

    private static void setDepth(int curNode, int d){
        tree.get(curNode).depth = d;
        for (int n: tree.get(curNode).children)
            setDepth(n, d+1);
    }

    private static int lca(int n1, int n2){
        int a = n1;
        int b = n2;
        while (tree.get(a).depth > tree.get(b).depth){
            a = tree.get(a).parent;
        }

        while (a != b){
            a = tree.get(a).parent;
            b = tree.get(b).parent;
        }
        return a;
    }

}

class Node{
    int parent;
    List<Integer> children;
    int depth;

    public Node(int p){
        this.parent = p;
        this.children = new ArrayList<>();
        this.depth = 0;
    }
}