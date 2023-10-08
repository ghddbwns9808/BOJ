import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[] stock;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T --> 0){
            N = Integer.parseInt(br.readLine());
            stock = new int[N];
            long sum = 0;
            int prevHighestDay = 0;
            int curHighestDay = 0;

            StringTokenizer tk = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++)
                stock[i] = Integer.parseInt(tk.nextToken());

            while (prevHighestDay < N){
                curHighestDay = findHighestDay(prevHighestDay);
                for (int i= prevHighestDay; i<curHighestDay; i++){
                    sum += stock[curHighestDay] - stock[i];
                }
                prevHighestDay = curHighestDay+1;
            }

            bw.write(sum + "\n");
        }

        bw.flush();
    }

    private static int findHighestDay(int startDay){
        int high = -1;
        int highestDay = startDay;
        for (int i=startDay; i<N; i++){
            if (stock[i] >= high){
                high = stock[i];
                highestDay = i;
            }
        }
        return highestDay;
    }

}