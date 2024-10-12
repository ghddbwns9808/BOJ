import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[] nums, sum;

    public static void main(String[] args) throws IOException {
        input();
        bw.write(solve() + "");
        bw.flush();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        sum = new int[(N+1) * N / 2];

        for (int i=0; i<N; i++)
            nums[i] = Integer.parseInt(br.readLine());
    }

    private static int solve(){
        calcSum();
        Arrays.sort(sum);
        Arrays.sort(nums);

        for (int i=N-1; i>0; i--){
            for (int j=i-1; j>=0; j--){
                int target = nums[i] - nums[j];
                if (isPossible(target)){
                    return nums[i];
                }
            }
        }
        return 0;
    }

    private static void calcSum(){
        int idx = 0;
        for (int i=0; i<N; i++){
            for (int j=i; j<N; j++){
                sum[idx++] = nums[i] + nums[j];
            }
        }
    }

    private static boolean isPossible(int target){
        int s = 0;
        int e = sum.length-1;

        while (s < e){
            int mid = (s + e) / 2;
            if (sum[mid] == target)
                return true;
            if (sum[mid] > target){
                e = mid;
            }else{
                s = mid + 1;
            }
        }
        return false;
    }

}