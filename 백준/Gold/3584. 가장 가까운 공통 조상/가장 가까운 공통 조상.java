import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static List<Node> tree;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T --> 0){
            //child 노드에 등장한 수를 모두 더할 변수
            int tmpSum = 0;
            N = Integer.parseInt(br.readLine());

            //tree 초기화
            tree = new ArrayList<>();
            for (int i=0; i<=N; i++)
                tree.add(new Node(i));

            for(int i=1; i<N; i++){
                StringTokenizer tk = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(tk.nextToken());
                int c = Integer.parseInt(tk.nextToken());
                //tmpSum에 child 값 더하기
                tmpSum += c;

                //트리에 값 입력
                tree.get(p).children.add(c);
                tree.get(c).parent = p;
            }

            //1 ~ N 까지 수를 더했을 때의 값
            int sum = N%2 == 0 ? (1 + N) * (N/2) : (1 + N) * (N/2) + (N/2)+1;
            //sum - tmpSum 하면
            //child 노드로 한번도 등장하지 않은 -> parent가 없는 -> root 노드를 찾을 수 있음
            int root = sum - tmpSum;

            //루트 노드의 depth를 0으로 시작해서 depth 설정하기
            setDepth(root, 0);

            StringTokenizer tk = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(tk.nextToken());
            int n2 = Integer.parseInt(tk.nextToken());

            //lca(n1, n2)를 무조건 n1의 depth가 n2의 depth보다 낮지 않게 시작하기 위해
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
        //두 노드의 depth가 일치할 때 까지 a를 위로 올리기
        while (tree.get(a).depth > tree.get(b).depth){
            a = tree.get(a).parent;
        }

        //같은 조상을 찾을 때 까지 위로 올려주기
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

/*
1. lca 알고리즘입니다.
2. root 노드를 지정하지 않았기 때문에 root 노드를 찾는 과정이 필요합니다.
3. 이때 노드가 최대 10000개이기 때문에 빠르게 찾을 방법을 생각해봤습니다.
 */