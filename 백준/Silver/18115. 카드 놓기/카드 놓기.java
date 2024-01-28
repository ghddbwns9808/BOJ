import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        StringTokenizer tk = new StringTokenizer(new StringBuilder(br.readLine()).reverse().toString());
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 1; i <=N ; i++) {
            int num = Integer.parseInt(tk.nextToken());

            if (num == 1) {
                deque.addFirst(i);
            } else if (num == 2) {
                int top = deque.removeFirst();
                deque.addFirst(i);
                deque.addFirst(top);
            } else {
                deque.addLast(i);
            }
        }

        while (deque.size() != 0)
            bw.write(deque.removeFirst() + " ");
        bw.flush();
    }
}