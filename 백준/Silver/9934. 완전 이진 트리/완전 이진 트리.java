import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int K;
    static int[] tree, input;

    public static void main(String[] args) throws IOException {
        input();
        makeTree(0, input.length, 1);

        for (int i=0; i<K; i++){
            for (int j=(int)Math.pow(2, i); j<(int)Math.pow(2, i+1); j++){
                bw.write(tree[j]+" ");
            }
            bw.newLine();
        }

        bw.flush();
    }

    private static void input() throws IOException{
        K = Integer.parseInt(br.readLine());
        tree = new int[(int) Math.pow(2, K)];
        input = new int[tree.length-1];
        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<input.length; i++)
            input[i] = Integer.parseInt(tk.nextToken());
    }

    private static void makeTree(int start, int end, int idx){
        tree[idx] = input[(start+end)/2];

        if (start+1 != end) {
            makeTree(start, (start + end) / 2, 2*idx);
            makeTree((start + end) / 2 + 1, end, 2*idx+1);
        }
    }

    private static void printTree(int depth){

    }
}
