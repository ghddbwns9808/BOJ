import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int ans;


    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();

        int n = 3;
        while (n-->0){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tk.nextToken());
            int y = Integer.parseInt(tk.nextToken());

            xMap.putIfAbsent(x, 0);
            yMap.putIfAbsent(y, 0);

            xMap.replace(x, xMap.get(x) + 1);
            yMap.replace(y, yMap.get(y) + 1);
        }

        for (int k: xMap.keySet()){
            if (xMap.get(k) == 1)
                bw.write(k+" ");
        }

        for (int k: yMap.keySet()){
            if (yMap.get(k) == 1)
                bw.write(k+"");
        }

        bw.flush();
    }
}
