import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());
        numbers = new int[n];

        tk = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++)
            numbers[i] = Integer.parseInt(tk.nextToken());

        int curSum = 0;
        int maxV = 0;
        int start = 0;
        int end = 0;

        while (end < n){
            if (numbers[end] > m){
                end++;
                start = end;
                curSum = 0;
                continue;
            }
            else if (numbers[end] == m){
                maxV = m;
                break;
            }

            curSum += numbers[end];
            end++;
            if (curSum > m){
                while (curSum > m){
                    curSum -= numbers[start];
                    start++;
                }
            }
            maxV = Math.max(maxV, curSum);
            if (maxV == m)
                break;
        }

        bw.write(maxV + "");
        bw.flush();
    }
}
