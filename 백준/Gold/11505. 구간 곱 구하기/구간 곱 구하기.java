import org.w3c.dom.xpath.XPathResult;

import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, K;
    static SegmentTree st;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        input();
        solve();

        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());

        nums = new int[N+1];
        for (int i=1; i<=N; i++)
            nums[i] = Integer.parseInt(br.readLine());

        st = new SegmentTree(N);
        st.init(1, N, 1, nums);
    }

    private static void solve() throws IOException {
        for (int i=0; i<M+K; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());
            int c = Integer.parseInt(tk.nextToken());

            if (a == 1){
                st.update(1, N, b, 1, c);
                nums[b] = c;
            }else{
                bw.write(st.sum(1, N, b, (int)c, 1) + "\n");
            }
        }
    }
}

class SegmentTree{
    long[] tree;
    int treeSize;
    static final int MOD =  1000000007;

    public SegmentTree(int arrSize){
        int treeHeight = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
        this.treeSize = (int) Math.pow(2, treeHeight + 1);
        tree = new long[treeSize];
    }

    public long init(int start, int end, int node, int[] arr){
        if (start == end)
            return this.tree[node] = arr[start];

        return tree[node] = (init(start, (start + end)/2, 2*node, arr)
                * init((start + end)/2 + 1, end, 2*node+1, arr)) % MOD;
    }

    public long update(int start, int end, int idx, int node, int change){
        if (idx<start || idx>end) return tree[node];

        if (start == end)
            return tree[node] = change;

        return tree[node] = (update(start, (start+end)/2, idx, node*2, change) *
            update((start+end)/2 + 1, end, idx, node*2 + 1, change))%MOD;
    }

    public long sum(int start, int end, int left, int right, int node){
        if (left>end || right<start) return 1;

        if (left<=start && end <= right) return tree[node];

        return (sum(start, (start+end)/2, left, right, node*2)
                * sum((start+end)/2+1, end, left, right, node*2+1)) % MOD;
    }
}