import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int ans;


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-->0){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int ans = 0;
            while (N-->0){
                ans += Integer.parseInt(tk.nextToken());
            }
            bw.write(ans+"\n");
        }
        bw.flush();
    }
}
