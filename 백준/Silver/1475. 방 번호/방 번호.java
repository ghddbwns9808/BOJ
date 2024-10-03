import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int M, ans;
    static String N;
    static Map<Integer, Integer> count;

    public static void main(String[] args) throws IOException {
        N = br.readLine();
        M = N.length();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        count = new HashMap<>();
        for (int i=0; i<9; i++)
            count.put(i, 0);

        for (int i=0; i<M; i++){
            int n = Character.getNumericValue(N.charAt(i));
            if (n == 9) n = 6;
            count.replace(n, count.get(n) + 1);
        }

        for (int i=0; i<9; i++)
            pq.offer(count.get(i));

        ans = pq.poll();
        int tmp = 0;

        if (ans == count.get(6)) {
            tmp = ans;
            ans = Math.max(pq.poll(), tmp/2 + tmp%2);
        }

        bw.write(ans+"");
        bw.flush();
    }
}
