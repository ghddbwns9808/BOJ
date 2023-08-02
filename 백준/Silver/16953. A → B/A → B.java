import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(tk.nextToken());
        long b = Integer.parseInt(tk.nextToken());

        HashMap<Long, Long> minCnt = new HashMap<>();
        Queue<Long> q = new LinkedList<>();
        minCnt.put(a, 0L);
        q.offer(a);
        while (!q.isEmpty()){
            long cur = q.poll();

            long addOne = Long.parseLong(cur + "1");
            long multiTwo = cur*2;
            
            long[] cadidates = new long[]{addOne, multiTwo};

            for(long n: cadidates){
                if(n <= b){
                    if(!minCnt.containsKey(n)){
                        minCnt.put(n, minCnt.get(cur) + 1);
                        q.offer(n);
                    }else{
                        if(minCnt.get(n) > minCnt.get(cur) + 1){
                            minCnt.put(n, minCnt.get(cur) + 1);
                            q.offer(n);
                        }
                    }
                }
            }
        }
        if (!minCnt.containsKey(b))
            System.out.println(-1);
        else
            System.out.println(minCnt.get(b)+1);
    }
}
