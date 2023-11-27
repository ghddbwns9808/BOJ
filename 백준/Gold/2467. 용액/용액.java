import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, minSum, smallNum, bigNum;
    static int[] inputArr;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        minSum = Integer.MAX_VALUE;
        inputArr = new int[N];

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            inputArr[i] = Integer.parseInt(tk.nextToken());

        int start = 0;
        int end = N-1;

        while (start < end){
            int tmp = inputArr[start] + inputArr[end];
            if (minSum > Math.abs(tmp)){
                smallNum = inputArr[start];
                bigNum = inputArr[end];

                minSum = Math.abs(tmp);
            }
            if (tmp == 0) break;
            else if (tmp > 0) end--;
            else start++;
        }

        bw.write(smallNum + " " + bigNum);
        bw.flush();
    }

}
