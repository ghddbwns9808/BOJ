import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        int M = Integer.parseInt(br.readLine());
        int set = 0;
        while (M-- > 0){
             StringTokenizer tk = new StringTokenizer(br.readLine());
             int n;
             switch (tk.nextToken()){
                 case "add":
                     n = Integer.parseInt(tk.nextToken());
                     set |= (1<<n);
                     break;
                 case "remove":
                     n = Integer.parseInt(tk.nextToken());
                     set &= ~(1<<n);
                     break;
                 case "check":
                     n = Integer.parseInt(tk.nextToken());
                     if ((set & (1<<n)) > 1) bw.write("1\n");
                     else bw.write("0\n");
                     break;
                 case "toggle":
                     n = Integer.parseInt(tk.nextToken());
                     set ^= (1<<n);
                     break;
                 case "all":
                     set = (1<<21) - 1;
                     break;
                 case "empty":
                     set = 0;
                     break;
             }
        }

        bw.flush();
        bw.close();
    }
}
