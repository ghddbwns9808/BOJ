import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(br.readLine());

        BigInteger n = new BigInteger(tk.nextToken());
        BigInteger m = new BigInteger(tk.nextToken());
        System.out.print(n.divide(m) + "\n" + n.remainder(m));
    }
}