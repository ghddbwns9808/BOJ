import org.w3c.dom.xpath.XPathResult;

import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, Q;
    static SegmentTree tree;
    static long[] nums;

    public static void main(String[] args) throws IOException {
        input();
        solve();

        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        Q = Integer.parseInt(tk.nextToken());

        nums = new long[N+1];
        tk = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++)
            nums[i] = Long.parseLong(tk.nextToken());

        tree = new SegmentTree(N);
        tree.init(1, N, 1, nums);
    }

    private static void solve() throws IOException {
        for (int i=0; i<Q; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tk.nextToken());
            int y = Integer.parseInt(tk.nextToken());
            int a = Integer.parseInt(tk.nextToken());
            long b = Long.parseLong(tk.nextToken());

            bw.write(tree.sum(1, N, Math.min(x, y), Math.max(x, y), 1)+"\n");
            tree.update(1, N, a, 1, b-nums[a]);
            nums[a] = b;
        }
    }
}

class SegmentTree{
    long[] tree;
    int treeSize;

    public SegmentTree(int arrSize){
        int treeHeight = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
        this.treeSize = (int) Math.pow(2, treeHeight + 1);
        tree = new long[treeSize];
    }

    public long init(int start, int end, int node, long[] arr){
        if (start == end)
            return this.tree[node] = arr[start];

        return tree[node] = init(start, (start + end)/2, 2*node, arr)
                + init((start + end)/2 + 1, end, 2*node+1, arr);
    }

    public void update(int start, int end, int idx, int node, long change){
        if (idx<start || idx>end) return;

        tree[node] += change;

        if (start != end) {
            update(start, (start + end) / 2, idx, node * 2, change) ;
            update((start + end) / 2 + 1, end, idx, node * 2 + 1, change);
        }
    }

    public long sum(int start, int end, int left, int right, int node){
        if (left>end || right<start) return 0;

        if (left<=start && end <= right) return tree[node];

        return sum(start, (start+end)/2, left, right, node*2)
                + sum((start+end)/2+1, end, left, right, node*2+1);
    }
}