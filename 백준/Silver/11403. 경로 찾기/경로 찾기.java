import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        input();
        floydWarshall();

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if (dist[i][j] == Integer.MAX_VALUE) bw.write("0 ");
                else bw.write("1 ");
            }
            bw.newLine();
        }
        bw.flush();
    }

    private static void input() throws IOException{
        N = Integer.parseInt(br.readLine());

        dist = new int[N][N];
        for (int i=0; i<N; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                 int weight = Integer.parseInt(tk.nextToken());
                 if (weight == 0) dist[i][j] = Integer.MAX_VALUE;
                 else dist[i][j] = 1;
            }
        }
    }

    private static void floydWarshall(){
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if (dist[j][i] == Integer.MAX_VALUE) continue;

                for (int k=0; k<N; k++){
                    if (dist[i][k] == Integer.MAX_VALUE) continue;
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }
    }

}