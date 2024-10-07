import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int ans;


    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        long a = Long.parseLong(tk.nextToken());
        long b = Long.parseLong(tk.nextToken());
        System.out.println((a+b) * (a-b));
    }
}
