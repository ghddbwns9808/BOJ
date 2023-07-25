import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());

        for (int i=0; i<n; i++){
            long command = Long.parseLong(br.readLine());
            if (command == 0){
                if (pq.isEmpty())
                    bw.write(0+"\n");
                else
                    bw.write(pq.poll() + "\n");
            }else{
                pq.offer(command);
            }
        }
        bw.flush();
        bw.close();

    }
}
