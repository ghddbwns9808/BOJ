import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        int[] b = new int[N];
        int s = 0;

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            a[i] = Integer.parseInt(tk.nextToken());
        tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            b[i] = Integer.parseInt(tk.nextToken());

        Arrays.sort(a);
        Arrays.sort(b);

        for (int i=0; i<N; i++)
            s += a[i] * b[N-i-1];

        bw.write(s+"");
        bw.flush();
    }
}