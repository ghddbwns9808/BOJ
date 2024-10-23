import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        input();
        bw.flush();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(tk.nextToken());
        Tree tree = new Tree(arr);

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            tk = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(tk.nextToken());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            if (command == 1) {
                arr[a] = b;
                tree.change(1, a, b, 1, N);
            } else {
                int minValue = tree.query(1, a, b, 1, N);
                for (int i = a; i <= b; i++) {
                    if (arr[i] == minValue) {
                        bw.write(i + "\n");
                        break;
                    }
                }
            }
        }
    }

}

class Tree {
    int[] nodes, arr;

    public Tree(int[] arr) {
        this.arr = arr;
        nodes = new int[arr.length * 4];
        build(1, 1, arr.length - 1);
    }

    private int build(int node, int start, int end) {
        if (start == end) {
            return nodes[node] = arr[start];
        }
        int mid = (start + end) / 2;
        int left = build(2 * node, start, mid);
        int right = build(2 * node + 1, mid + 1, end);

        return nodes[node] = Math.min(left, right);
    }

    public int change(int node, int idx, int v, int start, int end) {
        if (idx < start || idx > end) {
            return nodes[node];
        }

        if (start == end) {
            return nodes[node] = v;
        }

        int mid = (start + end) / 2;
        int left = change(2 * node, idx, v, start, mid);
        int right = change(2 * node + 1, idx, v, mid + 1, end);

        return nodes[node] = Math.min(left, right);
    }

    public int query(int node, int left, int right, int start, int end) {
        if (left <= start && right >= end) {
            return nodes[node];
        }

        if (right < start || left > end) {
            return Integer.MAX_VALUE;
        }

        int mid = (start + end) / 2;
        int leftChild = query(2 * node, left, right, start, mid);
        int rightChild = query(2 * node + 1, left, right, mid + 1, end);
        return Math.min(leftChild, rightChild);
    }

}