import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] upper, lower, crashes;

    static int n,h;

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        h = Integer.parseInt(tk.nextToken());

        upper = new int[n/2];
        lower = new int[n/2];
        //높이 h에서 파괴되는 장애물의 수를 저장할 배열
        crashes = new int[h];

        //위 아래 따로 입력
        for (int i=0; i<n; i++){
            int m = Integer.parseInt(br.readLine());
            if (i%2 == 0) lower[i/2] = m;
            else upper[i/2] = m;
        }

        //이분탐색을 위한 정렬
        Arrays.sort(upper);
        Arrays.sort(lower);

        //0부터 높이까지 반복하며 해당 높이에 존재하는 장애물 갯수 찾기
        for (int i=0; i<crashes.length; i++)
            crashes[i] = (n/2 - binarySearch(i+1, lower)) + (n/2- binarySearch(h-i, upper));

        //최소값과 최소값 갯수 카운팅
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i=0; i<h; i++){
            if (min > crashes[i]) {
                min = crashes[i];
                cnt = 0;
            }
            if (min == crashes[i])
                cnt++;
        }

        bw.write(min + " " + cnt);
        bw.flush();
    }

    private static int binarySearch(int target, int[] arr){
        int start = 0;
        int end = n/2;

        while (start < end){
            int mid = (start + end)/2;
            if (arr[mid] < target)
                start = mid + 1;
            else
                end = mid;
        }
        return start;
    }

}