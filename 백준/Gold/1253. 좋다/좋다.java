import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static long[] numbers;
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        long cnt = 0;
        numbers = new long[n];

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++)
            numbers[i] = Long.parseLong(tk.nextToken());

        // n이 3보다 작으면 2개의 합이 한개의 수가 될 수 없다
        // 0 찍고 끝내기
        if (n < 3){
            bw.write(0);
            bw.flush();
            return;
        }
        //숫자를 정렬
        Arrays.sort(numbers);

        for(int i=0; i<n; i++){
            //num[start] + num[end] 가 target이 되어야 한다.
            long target = numbers[i];
            int start = 0;
            int end = n-1;
            
            //첫 숫자에 대해 검사하면 첫 숫자 기준 오른쪽만 검사
            if (i == 0)
                start = 1;
            //마지막 숫자에 대해 검사하면 마지막 숫자 기준 왼쪽만 검사
            if (i == n-1)
                end = n-2;

            //투포인터 검사 들어갑니데이~
            while (start < end){
                long sum = numbers[start] + numbers[end];
                if (sum == target){
                    cnt++;
                    break;
                }else{
                    //start 또는 end가 가리키는 지점이 검사하는 숫자와 같다면 한개 더 이동합니데이
                    if (sum > target){
                        end--;
                        if (end == i)
                            end--;
                    }else{
                        start++;
                        if (start == i)
                            start++;
                    }
                }
            }
        }
        bw.write(cnt+"");
        bw.flush();
    }
}