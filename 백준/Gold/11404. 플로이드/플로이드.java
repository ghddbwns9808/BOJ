import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        input();
        floydWarshall();

        for (int i=1; i<=N; i++){
            for (int j=1; j<=N; j++){
                if (dist[i][j] == Integer.MAX_VALUE) bw.write("0 ");
                else bw.write(dist[i][j] + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }

    
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1][N+1];
        for (int i=0; i<=N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        while (M --> 0){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tk.nextToken());
            int to = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            dist[from][to] = Math.min(weight, dist[from][to]);
        }
    }

    private static void floydWarshall(){
        /*
        i = 중간에 거칠 노드
        j = 시작 노드
        k = 도착 노드
         */
        for (int i=1; i<=N; i++){
            for (int j=1; j<=N; j++){
                //시작 -> 중간 노드가 경로가 없으면 건너뛰고
                if (dist[j][i] == Integer.MAX_VALUE) continue;
                //경로가 있으면 중간 -> 도착 노드로 가는 길이 있는지 확인
                for (int k=1; k<=N; k++){
                    if (dist[i][k] == Integer.MAX_VALUE) continue;
                    //j -> i 경로와 i -> k 경로가 모두 존재한는 경우
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }
    }
}