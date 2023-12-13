import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, maxLevel, maxWidth, treeLevel, root;
    static List<Node> tree;
    static boolean[][] matrix;

    public static void main(String[] args) throws IOException {
        input();
        countSubTree(root);
        makeTree(root, 0, 0);
        solve();
//        System.out.println("Root: " + root);
//        for (int i=1; i<=N; i++){
//            System.out.println(tree.get(i).left + ", " + tree.get(i).right);
//        }
//        for (int i=0; i<=treeLevel; i++){
//            for (int j=0; j<N; j++)
//                System.out.print(matrix[i][j] + " ");
//            System.out.println();
//        }
        bw.write(maxLevel + " " + maxWidth);
        bw.flush();
    }

    private static void input() throws IOException{
        maxLevel = Integer.MIN_VALUE;
        maxWidth = Integer.MIN_VALUE;

        N = Integer.parseInt(br.readLine());
        matrix = new boolean[1000][N];
        tree = new ArrayList<>();
        for (int i=0; i<=N; i++)
            tree.add(new Node());

        for (int i=0; i<N; i++) {
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(tk.nextToken());
            int left = Integer.parseInt(tk.nextToken());
            int right = Integer.parseInt(tk.nextToken());

            tree.get(n).init(left, right);
            if (left != -1) tree.get(left).hasParent = true;
            if (right != -1) tree.get(right).hasParent = true;
        }

        for (int i=1; i<=N; i++){
            if (!tree.get(i).hasParent){
                root = i;
                break;
            }
        }
    }

    private static void countSubTree(int node){
        int left = tree.get(node).left;
        int right = tree.get(node).right;

        if (left != -1){
            countSubTree(left);
            tree.get(node).leftCnt += tree.get(left).leftCnt;
            tree.get(node).leftCnt += tree.get(left).rightCnt;
            tree.get(node).leftCnt++;
        }
        if (right != -1){
            countSubTree(right);
            tree.get(node).rightCnt += tree.get(right).leftCnt;
            tree.get(node).rightCnt += tree.get(right).rightCnt;
            tree.get(node).rightCnt++;
        }
    }

    private static void makeTree(int node, int level, int start){
        int leftCnt = tree.get(node).leftCnt;
        int rightCnt = tree.get(node).rightCnt;
        treeLevel = Math.max(treeLevel, level);

        matrix[level][start+leftCnt] = true;
        if (leftCnt != 0)
            makeTree(tree.get(node).left, level+1, start);
        if (rightCnt != 0)
            makeTree(tree.get(node).right, level+1, start+leftCnt+1);
    }

    private static void solve(){
        for (int i=0; i<=treeLevel; i++){
            int startIdx = -1;
            int endIdx = -1;

            for (int j=0; j<N; j++){
                if (matrix[i][j]){
                    if (startIdx == -1){
                        startIdx = j;
                    }
                    endIdx = j;
                }
            }

            int width = endIdx-startIdx+1;
            if (width > maxWidth){
                maxWidth = width;
                maxLevel = i+1;
            }
        }
    }
}

class Node{
    int left, right, leftCnt, rightCnt;
    boolean hasParent = false;

    public void init(int l, int r){
        left = l;
        right = r;
    }
}
