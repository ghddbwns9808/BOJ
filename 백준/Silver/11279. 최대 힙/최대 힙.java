import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        while (n-->0){
            int command = Integer.parseInt(br.readLine());

            if (command == 0){
                if (pq.isEmpty()) bw.write("0\n");
                else bw.write(pq.poll()+"\n");
            }else{
                pq.offer(command);
            }
        }
        bw.flush();
    }
}
