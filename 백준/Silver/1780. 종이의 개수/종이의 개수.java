import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] board;
    private static int mOneCnt = 0;
    private static int zeroCnt = 0;
    private static int pOneCnt = 0;


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];


        for(int i=0; i<n; i++)
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

        divide(0, 0, n);
        bw.write(mOneCnt + "\n" + zeroCnt + "\n" + pOneCnt);
        bw.flush();
        bw.close();

    }
    private static void divide(int i, int j, int n){
        if (n == 1){
            plusCnt(board[i][j]);
        }else{
            if (isSameElementArray(i, j, n)){
                plusCnt(board[i][j]);
            }else{
                int newN = n/3;

                divide(i, j, newN);
                divide(i, j+newN, newN);
                divide(i, j + 2*newN, newN);

                divide(i + newN, j, newN);
                divide(i + newN, j+newN, newN);
                divide(i + newN, j + 2*newN, newN);

                divide(i + 2*newN, j, newN);
                divide(i + 2*newN, j + newN, newN);
                divide(i + 2*newN, j + 2*newN, newN);
            }
        }

    }

    private static boolean isSameElementArray(int i, int j, int n){
        int key = board[i][j];
        for (int a=0; a<n; a++){
            for(int b=0; b<n; b++){
                if (board[i+a][j+b] != key)
                    return false;
            }
        }
        return true;
    }

    private static void plusCnt(int key){
        switch (key){
            case -1:
                mOneCnt++;
                break;
            case 0:
                zeroCnt++;
                break;
            case 1:
                pOneCnt++;
                break;
        }
    }
}
