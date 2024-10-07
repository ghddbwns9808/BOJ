import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringTokenizer tk = new StringTokenizer(br.readLine());
        int n = 3;
        while (n-->0)
            pq.offer(Integer.parseInt(tk.nextToken()));
        pq.poll();
        System.out.println(pq.poll());
    }
}
