import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int t = Integer.parseInt(br.readLine());
        while (t--> 0){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            bw.write(Integer.parseInt(tk.nextToken()) + Integer.parseInt(tk.nextToken()) + "\n");
        }
        bw.flush();
    }
}