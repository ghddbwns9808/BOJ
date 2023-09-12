import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] numbers;
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        long cnt = 0;
        numbers = new int[n];

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++)
            numbers[i] = Integer.parseInt(tk.nextToken());
        //숫자를 정렬
        Arrays.sort(numbers);

        for(int i=1; i<n-1; i++){
            int start = 0;
            int end = n-1;
            int target = -1 * numbers[i];

            while (start < i && i <end ){
                int sum = numbers[start] + numbers[end];
                if (sum == target){
                    int rightCnt = 0;
                    int leftCnt = 0;

                    int initialLeft = numbers[start];
                    int initialRight = numbers[end];

                    while (true){
                        if (start == i)
                            break;
                        if (numbers[start] != initialLeft)
                            break;
                        start++;
                        leftCnt++;
                    }

                    while (true){
                        if (end == i)
                            break;
                        if (numbers[end] != initialRight)
                            break;
                        end--;
                        rightCnt++;
                    }
                    cnt += leftCnt * rightCnt;
                }
                else{
                    if (sum > target) end--;
                    else start++;
                }
            }
        }

        bw.write(cnt+"");
        bw.flush();
    }
}

/*
BOJ 3151
합이 0
골드 4

전략
1. 숫자 배열을 정렬
2. 왼쪽 끝을 start, 오른쪽 끝을 end로 하여 가운데로 좁혀가는 투포인터
3. start와 end의 합을 0으로 만들어주는 target을 start ~ end 사이에서 이분탐색으로 찾기
 */