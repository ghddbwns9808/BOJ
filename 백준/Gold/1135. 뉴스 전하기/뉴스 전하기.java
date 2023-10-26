import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[] dp;
    static List<Node> tree;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        dp = new int[N];

        for (int i=0; i<N; i++)
            tree.add(new Node());

        StringTokenizer tk = new StringTokenizer(br.readLine());
        tk.nextToken();
        for (int i=1; i<N; i++)
            tree.get(Integer.parseInt(tk.nextToken())).child.add(i);

        dp(0);

        bw.write(dp[0]+"");
        bw.flush();
    }

    static void dp(int cur){
        int tmpMax = 0;
        int wait = 1;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int c: tree.get(cur).child) {
            dp(c);
            pq.add(dp[c]);
        }

        while (!pq.isEmpty())
            tmpMax = Math.max(tmpMax, pq.poll()+ wait++);

        dp[cur] = tmpMax;
    }
}

class Node{
    List<Integer> child;

    public Node(){
        child = new ArrayList<>();
    }
}

/*
1. dp[i] : i번쨰 노드에서 서브트리에 있는 모든 노드까지 연락이 닿는데 걸리는 시간
2. child 노드의 모든 dp 값을 PriorityQueue에 넣는다(정렬)
3. dp[child] + 꺼낸 순서의 최댓값이 dp[cur]
    왜냐고요?
    child 노드가 3개 있고 각 dp값이 2, 1, 1 이라고 가정해봅시다, 각 차일드 노드를 a,b,c 노드로 가정합니다.
    greedy하게 오래 걸리는 순서대로 먼저 연락을 시작하는 것이 최고의 효율을 내는 것은 당연하쥬?
    그럼 dp값이 2인 노드 먼저 연락을 시작했을껍니다.
    이떄 나에서 연락이 가는 시간 1초를 더하면 3초가 걸리겠죠 => tmpMax는 3입니다.

    다음 노드를 꺼내봅시다. 1초가 소요되고 나에서 연락이 가는데 걸리는 시간 1초를 더하면 2초입니다.
    여기서 꺼낸 순서 1을 (0부터 시작합니다) 더해야합니다.
    a노드에 먼저 연락을 했으니 앞에 1초의 대기시간이 소요됐기 때문이죠.
    즉 1(dp[b]) + 1(cur에서 b까지 걸리는 시간) + 1(a노드에 먼저 연락하느라 지연된 시간) = 3 입니다.

    c노드도 같은 방식으로 1+1+2(대기 2초) = 4 가 되니
    tmpMax = 4

    즉 dp[cur] = 4가 됩니다.

4. root에서 시작해서 재귀적으로 top to bottom 형태의 재귀 dp를 풀고 마지막에 dp[root] 값을 출력합니다.
 */