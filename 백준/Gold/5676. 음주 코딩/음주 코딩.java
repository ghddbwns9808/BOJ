import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K;
    static Tree tree;

    public static void main(String[] args) throws IOException {
        input();
        bw.flush();
    }

    private static void input() throws IOException {
        while (true){
            String line = br.readLine();
            if (line == null || line.isBlank() || line.isEmpty())
                break;

            StringTokenizer tk = new StringTokenizer(line);
            N = Integer.parseInt(tk.nextToken());
            K = Integer.parseInt(tk.nextToken());

            int[] arr = new int[N+1];
            tk = new StringTokenizer(br.readLine());
            for (int i=1; i<=N; i++)
                arr[i] = Integer.parseInt(tk.nextToken());

            tree = new Tree(arr);

            while (K-->0){
//                tree.printTree();

                tk = new StringTokenizer(br.readLine());
                String command = tk.nextToken();
                int a = Integer.parseInt(tk.nextToken());
                int b = Integer.parseInt(tk.nextToken());

                if (command.equals("C")){
                    tree.change(1, a, b, 1, N);
                }else{
                    int res = tree.query(1, a, b, 1, N);
                    if (res > 0){
                        bw.write("+");
                    }else if (res < 0){
                        bw.write("-");
                    }else{
                        bw.write("0");
                    }
                }
            }
            bw.newLine();
        }
    }
}

class Tree {
    int[] tree, nodes;
    int n;

    public Tree(int[] nodes) {
        this.nodes = nodes;
        this.n = nodes.length-1;
        tree = new int[4 * n];
        build(1, 1, n);
    }

    private int build(int node, int start, int end) {
        if (start == end) {
            if (nodes[start] == 0){
                tree[node] = 0;
            }else{
                tree[node] = nodes[start] / Math.abs(nodes[start]);
            }
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftChild = build(2 * node, start, mid);
        int rightChild = build(2 * node + 1, mid + 1, end);
        return tree[node] = leftChild * rightChild;
    }

    public int query(int node, int left, int right, int start, int end){
        if (end < left || start > right)
            return 1;

        if (start >= left && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        int leftChild = query(2*node, left, right, start, mid);
        int rightChild = query(2*node+1, left, right, mid+1, end);
        return leftChild * rightChild;
    }

    public int change(int node, int idx, int v, int start, int end){
        if (start == end && start == idx){
            if (v == 0){
                return tree[node] = 0;
            }else{
                return tree[node] = v / Math.abs(v);
            }
        }

        if (idx < start || idx > end)
            return tree[node];

        int mid = (start + end) / 2;
        int leftChild = change(node*2, idx, v, start, mid);
        int rightChild = change(node*2 + 1, idx, v, mid+1, end);
        return tree[node] = leftChild * rightChild;
    }

    public void printTree(){
        for (int t: tree)
            System.out.print(t + " ");
        System.out.println();
    }
}