import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<ArrayList<Integer>> relation;
    static PriorityQueue<Integer> pq = new PriorityQueue();
    static boolean[] vst;
    static int[] score;
    static int N, M, ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();

        for(int i=1; i<=N; i++){
            if (score[i] > ans){
                pq.clear();
                pq.offer(i);
                ans = score[i];
            } else if (score[i] == ans) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty())
            bw.write(pq.poll() + " ");
        bw.flush();
        bw.close();
    }

    static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        score = new int[N+1];

        relation = new ArrayList();
        for(int i=0; i<=N; i++)
            relation.add(new ArrayList());

        for(int i=0; i<M; i++){
            tk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tk.nextToken());
            int b = Integer.parseInt(tk.nextToken());

            relation.get(a).add(b);
        }
    }

    static void solve(){
        for(int i=1; i<=N; i++){
            vst = new boolean[N+1];
            bfs(i);
        }
    }

    static void bfs(int start){
        vst[start] = true;
        Deque<Integer> q = new ArrayDeque<>();
        q.offerFirst(start);

        while (!q.isEmpty()){
            int cur = q.removeFirst();

            for(int nxt: relation.get(cur)){
                if (!vst[nxt]){
                    score[nxt]++;
                    vst[nxt] = true;
                    q.offerFirst(nxt);
                }
            }
        }
    }

}