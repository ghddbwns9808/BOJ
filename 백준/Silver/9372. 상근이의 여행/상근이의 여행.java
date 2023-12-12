import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-->0){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(tk.nextToken());
            int M = Integer.parseInt(tk.nextToken());
            while (M-->0)
                br.readLine();
            bw.write(N-1+"\n");
        }
        bw.flush();
    }
}
