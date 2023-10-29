import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    //진입차수
    static int[] inDegree, semester;
    static List<ArrayList<Integer>> graph;
    static Queue<int[]> q;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        inDegree = new int[N+1];
        semester = new int[N+1];
        graph = new ArrayList<>();
        q = new ArrayDeque<>();

        for (int i=0; i<=N; i++)
            graph.add(new ArrayList<>());

        while (M --> 0){
            tk = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(tk.nextToken());
            int post = Integer.parseInt(tk.nextToken());

            inDegree[post]++;
            graph.get(pre).add(post);
        }

        for (int i=1; i<=N; i++){
            if (inDegree[i] == 0) {
                q.add(new int[]{i, 1});
                inDegree[i]--;
            }
        }

        while (!q.isEmpty()){
            int[] cur = q.poll();
            semester[cur[0]] = cur[1];

            for (int n: graph.get(cur[0]))
                inDegree[n]--;

            for (int i=1; i<=N; i++){
                if (inDegree[i] == 0){
                    q.add(new int[]{i, cur[1]+1});
                    inDegree[i]--;
                }
            }
        }

        for (int i=1; i<=N; i++)
            bw.write(semester[i] + " ");
        bw.flush();
    }

}
/*
1. 위상 정렬 진입 차수가 0이 되는 과목들은 같은 학기에 수강할 수 있다
 */