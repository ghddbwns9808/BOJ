import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static long[] nums;

    public static void main(String[] args) throws IOException {
        input();
        long[] result = solve();
        for (long n: result)
            bw.write(n+" ");
        bw.flush();
    }

    private static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        nums = new long[N];

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            nums[i] = Long.parseLong(tk.nextToken());

        Arrays.sort(nums);
    }

    private static long[] solve(){
        long[] result = new long[3];
        long minSum = Long.MAX_VALUE;

        for (int i=0; i<N-2; i++){
            long key = nums[i];
            int left = i+1;
            int right = N-1;

            while (left < right){
                long sum = nums[left] + nums[right];
                long tmp = key + sum;

                if (tmp == 0){
                    result[0] = key;
                    result[1] = nums[left];
                    result[2] = nums[right];

                    return result;
                }else{
                    if (tmp < 0)
                        tmp *= -1;
                    if (tmp < minSum){
                        minSum = tmp;
                        result[0] = nums[i];
                        result[1] = nums[left];
                        result[2] = nums[right];
                    }
                    if (sum < -key) left++;
                    else right--;
                }
            }
        }
        return result;
    }
}