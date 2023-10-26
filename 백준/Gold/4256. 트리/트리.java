import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[][] tree;
    static int[] preOrder, inOrder;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T --> 0){
            //region 입력
            N = Integer.parseInt(br.readLine());
            tree = new int[N+1][2];
            preOrder = new int[N+1];
            inOrder = new int[N+1];
            visited = new boolean[N+1];
            Map<Integer, Boolean> nodes = new HashMap<>();
            for (int i=1; i<=N; i++){
                nodes.put(i, true);
            }

            StringTokenizer tk = new StringTokenizer(br.readLine());
            for (int i=1; i<=N; i++)
                preOrder[i] = Integer.parseInt(tk.nextToken());
            tk = new StringTokenizer(br.readLine());
            for (int i=1; i<=N; i++)
                inOrder[i] = Integer.parseInt(tk.nextToken());
            //endregion

            findRootNode(nodes, 1, N, 0, 0);

            postOrder(preOrder[1]);
            bw.write("\n");
        }
        bw.flush();
    }

    private static void postOrder(int cur) throws IOException {
        //Left Node가 존재하면 재귀호출
        if (tree[cur][0] != 0)
            postOrder(tree[cur][0]);
        //Right Node가 존재하면 재귀호출
        if (tree[cur][1] != 0)
            postOrder(tree[cur][1]);
        //Root 출력
        bw.write(cur+" ");
    }

    //전위 순회 결과로 subtree의 root node를 구하는 함수
    private static void findRootNode(Map<Integer, Boolean> subTreeNodes, int start, int end, int panent, int isLeft){
        for (int i=1; i<=N; i++){
            if (subTreeNodes.containsKey(preOrder[i])){
                tree[panent][isLeft] = preOrder[i];
                divideInTwoSubTree(preOrder[i], start, end);
                break;
            }
        }
    }

    //중위 순회 결과와 루트노드로 서브트리를 왼쪽과 오른쪽으로 나누는 함수
    private static void divideInTwoSubTree(int rootNode, int start, int end){
        Map<Integer, Boolean> leftNode = new HashMap<>();
        Map<Integer, Boolean> rightNode = new HashMap<>();
        int i = start;

        while (inOrder[i] != rootNode)
            leftNode.put(inOrder[i++], true);
        int rootIdx = i;
        while (i++<end)
            rightNode.put(inOrder[i], true);

        if (leftNode.size() > 0){
            //왼쪽 서브트리 노드가 하나밖에 없다? 부모 자식 관계 바로 설정
            if (leftNode.size() == 1){
                Iterator<Integer> child = leftNode.keySet().iterator();
                tree[rootNode][0] = child.next();
            }
            else
                findRootNode(leftNode, start, rootIdx-1, rootNode, 0);
        }

        if (rightNode.size() > 0){
            if (rightNode.size() == 1) {
                Iterator<Integer> child = rightNode.keySet().iterator();
                tree[rootNode][1] = child.next();
            }
            else
                findRootNode(rightNode, rootIdx+1, end, rootNode, 1);
        }
    }
}
/*
     1
   /  \
  2     3
 / \   / \
4   5  6  7

1. 전위 순회 결과로 서브트리의 루트노드를 구할 수 있습니다. (Root Left Right 순서니 가장 먼저 나오는 노드가 루트)
But 그 다음에 나오는 노드들 중 어디까지가 왼쪽에서 만들어지는 서브트리인지 구분이 안됩니다.
ex ) 위의 트리를 전위 순회한 결과입니다.
     1 2 3 4 5 6 7
     루트가 1인 것은 알지만 1의 Right Child 노드의 시작점을 알 수 없습니다.
2. 중위 순회의 결과로 왼쪽 오른쪽 서브트리를 구분할 수 있습니다.
ex) 위의 트리를 중위 순회한 결과입니다.
    4 2 5 1 6 3 7
    이떄 1번이 현재 서브트리의 루트인 것을 1번을 통해 구했습니다.
    1번 기준 왼쪽에 있는 숫자들(4, 2, 5)는 1번 노드를 기준으로 왼쪽에서 어떤 형태로든 서브트리를 구성합니다.
    또한 1번 기준 오른쪽 숫자들(6, 3, 7)은 오른쪽에서 서브트리를 구성합니다.
But 4, 2, 5번 노드들 그리고 6, 3, 7번 노드들 중 누가 루트노드인지 알 수 없습니다.
    이때 1번으로 돌아가보면 전위 순회 결과를 통해 누가 루트노드인지 알 수 있습니다.
ex) 1번은 방문 처리를 했으니 건너뛰고
    2 3 4 5 6 7 중 위에서 (4, 2, 5)가 왼쪽 서브트리 구성 노드인 것을 알고있으니 그 중 처음으로 등장하는 2번이 왼쪽 서브트리의 루트노드 입니다.
    루트(1)와 왼쪽 노드(4, 2, 5)를 제외한 나머지 노드들(3, 6, 7) 중 가장 먼저 등장한 노드가 오른쪽 서브트리의 루트노드가 됩니다((3)

3. 즉 전위 순회 결과로 서브트리의 루트를 구하고, 중위 순회 결과로 서브트리의 왼쪽 차일드 서브트리와 오른쪽 서브트리를 구성하는 노드들을 구분할 수 있습니다.
   이를 재귀적으로 호출하여 분할정복의 방식으로 트리를 재구성할 수 있습니다.

4. post order의 결과를 출력합니다.
 */