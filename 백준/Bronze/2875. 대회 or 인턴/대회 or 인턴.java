import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println(greedy(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
    }

    private static int greedy(int[] inputs){
        int remain_n = inputs[0];
        int remain_m = inputs[1];
        int remain_k = inputs[2];

        while (remain_k > 0){
            remain_k--;
            if (remain_n >= 2*remain_m){
                remain_n--;
            }else{
                remain_m--;
            }
            
        }
        if (remain_n >= 2* remain_m)
            return remain_m;
        else
            return remain_n /2;
    }
}
