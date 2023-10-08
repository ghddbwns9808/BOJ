import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static boolean[][] matrix, target;
    static int n, m;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());
        matrix = new boolean[n][m];
        target = new boolean[n][m];
        int cnt = 0;

        //초기 행렬 저장
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = input.charAt(j) == '0';
            }
        }
        //타겟 행렬 저장
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++)
                target[i][j] = input.charAt(j) == '0';
        }

        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= m - 3; j++) {
                if (matrix[i][j] != target[i][j]) {
                    switchMatrix(i, j);
                    cnt++;
                }
            }
        }

        if (isSameMatrix())
            bw.write(cnt + "");
        else
            bw.write("-1");

        bw.flush();
    }

    //[x][y]에서 시작해 3x3만큼 뒤집는 함수
    private static void switchMatrix(int x, int y) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                matrix[i][j] = !matrix[i][j];
            }
        }
    }

    //두 행렬이 같은 행렬인지 판단
    private static boolean isSameMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != target[i][j])
                    return false;
            }
        }
        return true;
    }
}
