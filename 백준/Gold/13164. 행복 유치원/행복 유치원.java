import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K;
    static int[] original, diffArr;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        K = Integer.parseInt(tk.nextToken());

        original = new int[N];
        diffArr = new int[N-1];

        //오리지널 input 받기
        tk = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++)
            original[i] = Integer.parseInt(tk.nextToken());

        //옆사람이랑 내 키 차이 저장할 배열
        for (int i=0; i<N-1; i++)
            diffArr[i] = original[i+1] - original[i];

        //정렬
        Arrays.sort(diffArr);

        //(N-1)개의 숫자에서 K-1개만큼 뺀 갯수를 작은 숫자 순서대로 더해주기
        int ans = 0;
        for (int i=0; i<N-K; i++)
            ans += diffArr[i];

        bw.write(ans+"");
        bw.flush();
    }
}
/*
1. 오른쪽 사람의 키에서 내 키를 뺀 값을 저장하는 배열을 만든다.(size: N-1)
2. 값이 큰 순서대로 K-1개만큼 제거
3. 남은 값들의 합
 */
