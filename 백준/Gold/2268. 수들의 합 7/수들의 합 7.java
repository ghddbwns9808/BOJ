import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static Tree tree;

    public static void main(String[] args) throws IOException {
        input();
        bw.flush();
    }

    private static void input() throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());

        long[] arr = new long[N+1];
        tree = new Tree(arr);

        while (M-->0){
            tk = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(tk.nextToken());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            if (command == 0){
                bw.write(tree.query(1, Math.min(a, b), Math.max(a, b), 1, N) + "\n");
            }else{
                tree.change(1, a, b, 1, N);
            }
        }
    }
}

class Tree {
    long[] tree, nodes;
    int n;

    public Tree(long[] nodes) {
        this.nodes = nodes;
        this.n = nodes.length-1;
        tree = new long[4 * n];
        build(1, 1, n);
    }

    private long build(int node, int start, int end) {
        if (start == end) {
            tree[node] = nodes[start];
            return tree[node];
        }

        int mid = (start + end) / 2;
        long leftChild = build(2 * node, start, mid);
        long rightChild = build(2 * node + 1, mid + 1, end);
        return tree[node] = leftChild + rightChild;
    }

    public long query(int node, int left, int right, int start, int end){
        if (end < left || start > right)
            return 0;

        if (start >= left && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        long leftChild = query(2*node, left, right, start, mid);
        long rightChild = query(2*node+1, left, right, mid+1, end);
        return leftChild + rightChild;
    }

    public long change(int node, int idx, int v, int start, int end){
        if (start == end && start == idx){
            return tree[node] = v;
        }

        if (idx < start || idx > end)
            return tree[node];

        int mid = (start + end) / 2;
        long leftChild = change(node*2, idx, v, start, mid);
        long rightChild = change(node*2 + 1, idx, v, mid+1, end);
        return tree[node] = leftChild + rightChild;
    }
}