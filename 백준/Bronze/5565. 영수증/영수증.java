import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int ans;


    public static void main(String[] args) throws IOException {
        int total = Integer.parseInt(br.readLine());
        int n = 9;
        while (n-->0)
            total -= Integer.parseInt(br.readLine());
        System.out.println(total);
    }
}
