import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[] numbers;

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int[] sizes = new int[6];

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<6; i++)
            sizes[i] = Integer.parseInt(tk.nextToken());

        tk = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(tk.nextToken());
        int P = Integer.parseInt(tk.nextToken());
        int shirts = 0;

        for (int n: sizes){
            shirts += n/T;
            if (n%T != 0)
                shirts++;
        }

        bw.write(shirts + "\n");
        bw.write(N/P + " " + N%P);
        bw.flush();
    }
}