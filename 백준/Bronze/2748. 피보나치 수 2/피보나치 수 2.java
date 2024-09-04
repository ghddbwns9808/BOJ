import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int n = Integer.parseInt(br.readLine());
        long[] fibonacci = new long[Math.max(n+1, 2)];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for (int i=2; i<=n; i++)
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];

        bw.write(fibonacci[n] + "");
        bw.flush();
    }

}