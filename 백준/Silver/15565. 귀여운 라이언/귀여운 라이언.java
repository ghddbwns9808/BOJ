import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, k;
    static boolean[] dolls;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(tk.nextToken());
        k = Integer.parseInt(tk.nextToken());
        dolls = new boolean[n];
        int ryanCnt = 0;
        tk = new StringTokenizer(br.readLine());
        //1이면 true 2면 false
        for (int i = 0; i < n; i++){
            if (Integer.parseInt(tk.nextToken()) == 1) {
                dolls[i] = true;
                ryanCnt++;
            }
            else dolls[i] = false;
        }
        //라이언 인형 갯수가 k개보다 적으면 집합 존재 불가능
        if(ryanCnt < k){
            bw.write("-1");
            bw.flush();
            return;
        }

        int minLen = Integer.MAX_VALUE;

        //curDolls[0] = 라이언 인형 갯수
        //curDolls[1] = 어피치 인형 갯수
        int[] curDolls = new int[]{0,0};
        int start = 0;
        int end = 0;

        while (end < n){
            //이번 인형이 라이언 인형이면?
            if (dolls[end]){
                //이미 라이언 인형이 k개 들어가있다?
                if (curDolls[0] == k){
                    //start가 가리키는 곳이 라이언이면{
                    if (dolls[start])
                        start++;

                    //start가 가리키는 곳이 라이언이 될 때 까지 start++
                    while (!dolls[start]){
                        curDolls[1]--;
                        start++;
                    }
                }else
                    curDolls[0]++;
                //이번 인형이 라이언이어서 라이언 인형 갯수가 k개가 됐다?
                if (curDolls[0] == k) {
                    while (!dolls[start]){
                        curDolls[1]--;
                        start++;
                    }
                    minLen = Math.min(minLen, curDolls[0] + curDolls[1]);
                }
            }
            //어피치면 그냥 추가
            else
                curDolls[1]++;
            end++;
        }
        bw.write(minLen + "");
        bw.flush();
    }
}
