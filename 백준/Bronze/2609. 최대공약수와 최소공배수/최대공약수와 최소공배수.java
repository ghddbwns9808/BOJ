import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(tk.nextToken());
        int b = Integer.parseInt(tk.nextToken());
        int gcd = euclidean(Math.max(a, b), Math.min(a, b));

        bw.write(gcd + "\n" + a * b / gcd);
        bw.flush();
    }

    private static int euclidean(int a, int b){
        if ((a%b) == 0)
            return b;
        return euclidean(b, a%b);
    }
}