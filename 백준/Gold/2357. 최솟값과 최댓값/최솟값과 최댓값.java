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

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        tree = new Tree(arr);

        while (M-- > 0) {
            tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            int[] res = tree.query(1, a, b, 1, N);
            bw.write(res[1] + " " + res[0] + "\n");
        }
    }
}

class Tree {
    int[] nodes;
    int[][] tree;
    int n;

    public Tree(int[] nodes) {
        this.nodes = nodes;
        this.n = nodes.length - 1;
        tree = new int[4 * n][2];
        build(1, 1, n);
    }

    private int[] build(int node, int start, int end) {
        if (start == end) {
            tree[node][0] = nodes[start];
            tree[node][1] = nodes[start];
            return tree[node];
        }

        int mid = (start + end) / 2;
        int[] leftChild = build(2 * node, start, mid);
        int[] rightChild = build(2 * node + 1, mid + 1, end);
        tree[node][0] = Math.max(leftChild[0], rightChild[0]);
        tree[node][1] = Math.min(leftChild[1], rightChild[1]);
        return tree[node];
    }

    public int[] query(int node, int left, int right, int start, int end) {
        if (end < left || start > right)
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};

        if (start >= left && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        int[] leftChild = query(2 * node, left, right, start, mid);
        int[] rightChild = query(2 * node + 1, left, right, mid + 1, end);
        return new int[]{Math.max(leftChild[0], rightChild[0]), Math.min(leftChild[1], rightChild[1])};
    }
}