import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static ArrayList<ArrayList<Integer>> neighbor = new ArrayList<>();
    static boolean[] visited;
    static int[] result;
    static int n;

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        int m = Integer.parseInt(tk.nextToken());

        int curMax = Integer.MIN_VALUE;

        result = new int[n+1];

        for(int i=0;i<=n;i++){
            neighbor.add(new ArrayList<>());
        }

        //인접 리스트로 그래프 만들기
        //근데 이제 간선 방향을 반대로 저장해요
        while(m-->0){
            tk = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(tk.nextToken());
            int from = Integer.parseInt(tk.nextToken());

            neighbor.get(to).add(from);
        }

        //dfs 돌리기
        for(int i=1; i<=n; i++){
            visited = new boolean[n+1];
            dfs(i);
        }
        
        //max 값 찾기
        for(int i=1; i<=n; i++) 
            curMax = Math.max(curMax, result[i]);

        //result 배열을 돌며 max값과 같으면 출력 -> 자동 오름차순
        for(int i=1; i<=n; i++) {
            if (result[i] == curMax)
                bw.write(i + " ");
        }
        bw.flush();
    }

    private static void dfs(int start){
        Stack<Integer> s = new Stack<>();
        s.add(start);
        visited[start] = true;

        while (!s.isEmpty()){
            int cur = s.pop();

            for(int i: neighbor.get(cur)){
                if(!visited[i]){
                    s.push(i);
                    visited[i] = true;
                    //다른건 dfs랑 다 독같은데 방문하는 노드의 result값을 ++ 해줍니다
                    result[i]++;
                }
            }
        }
    }
}