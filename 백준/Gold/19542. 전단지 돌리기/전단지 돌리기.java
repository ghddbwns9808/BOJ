import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, S, D, cnt;
    static int[] deepestChild;
    static List<ArrayList<Integer>> tree;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        S = Integer.parseInt(tk.nextToken());
        D = Integer.parseInt(tk.nextToken());

        visited = new boolean[N+1];
        deepestChild = new int[N+1];
        tree = new ArrayList<>();
        for (int i=0; i<=N; i++)
            tree.add(new ArrayList<>());

        for (int i=1; i<N; i++){
            tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        setDeepestChild(S);

        visited = new boolean[N+1];
        postOrder(S);

        bw.write(cnt-1 + "");
        bw.flush();
    }

    private static int setDeepestChild(int cur){
        int maxD = 0;
        visited[cur] = true;
        for (int c: tree.get(cur)){
            if (!visited[c])
                maxD = Math.max(maxD, setDeepestChild(c) + 1);
        }

        deepestChild[cur] = maxD;
        return maxD;
    }

    private static void postOrder(int cur){
        visited[cur] = true;
        cnt++;
        for (int c: tree.get(cur)){
            if (!visited[c] && deepestChild[c] >= D){
                cnt++;
                postOrder(c);
            }
        }
    }
}
/*
1. 루트노드에서 출발해 자식노드를 재귀적으로 들어가기
2. int[] deepestChild[]에 내 서브트리에서 가장 깊게 들어갈 수 있는 깊이를 저장
3.  deepestChild[i]가 힘D 보다 크거나 같으면 child노드로 들어가야됨 == 방문해야됨
    -> 같은 경우엔 1번만 방문하면 되고, 크면 2번 방문해야됨
 */