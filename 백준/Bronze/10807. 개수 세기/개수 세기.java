import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        StringTokenizer tk = new StringTokenizer(br.readLine());
        int[] input = new int[N];
        for (int i=0; i<N; i++){
            input[i] = Integer.parseInt(tk.nextToken());
        }
        int v = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++){
            if (input[i] == v)
                cnt++;
        }

        System.out.println(cnt);
    }
}