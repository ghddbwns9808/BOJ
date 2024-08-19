import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] numbers;
    static int N, M;


    public static void main(String[] args) throws IOException{
        input();
        solve();
        bw.flush();
    }

    private static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            numbers[i] = Integer.parseInt(tk.nextToken());
        Arrays.sort(numbers);

        M = Integer.parseInt(br.readLine());
    }

    private static void solve() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++)
            bw.write(binarySearch(Integer.parseInt(tk.nextToken())));
    }

    private static String binarySearch(int target){
        int left = 0;
        int right = N;

        while (left < right){
            int mid = (left + right) / 2;
            if (numbers[mid] == target)
                return "1\n";
            else if (numbers[mid] > target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return "0\n";
    }
}