import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static boolean[] vst;

    public static void main(String[] args) throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        M = Integer.parseInt(tk.nextToken());
        vst = new boolean[N];
        backtracking(new int[M], 0);
        bw.flush();
    }

    private static void backtracking(int[] arr, int depth) throws IOException {
        if (depth == M){
            printArray(arr);
            return;
        }

        for (int i = 0; i<N; i++){
            if (vst[i]) continue;
            arr[depth] = i;
            vst[i] = true;
            backtracking(arr, depth+1);
            vst[i] = false;
        }
    }

    private static void printArray(int[] arr) throws IOException {
        for (int n: arr)
            bw.write((n+1) + " ");
        bw.newLine();
    }

}