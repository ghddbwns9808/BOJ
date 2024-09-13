import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(tk.nextToken());
        int s = Integer.parseInt(tk.nextToken());
        System.out.println(2*s - r1);
    }
}