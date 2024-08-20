import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        BigInteger A = new BigInteger(br.readLine());
        BigInteger B = new BigInteger(br.readLine());
        BigInteger sum = A.add(B);
        BigInteger minus = A.subtract(B);
        BigInteger mul = A.multiply(B);

        bw.write(sum +"\n" + minus + "\n" + mul);
        bw.flush();
    }
}