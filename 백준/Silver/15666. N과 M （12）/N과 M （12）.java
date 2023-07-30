import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n,m;
    private static int[] numbers;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());
        numbers = new int[n+1];
        tk = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++)
            numbers[i] = Integer.parseInt(tk.nextToken());

        Arrays.sort(numbers);
        solution(new int[m], 1, 0);
        System.out.print(sb);
    }

    private static void solution(int[] cur, int startFrom, int depth){
        if(depth == m) {
            for(int n : cur)
                sb.append(n+" ");
            sb.append("\n");
            return;
        }
        for (int i = startFrom; i <= n; i++) {
            if (numbers[i] == numbers[i-1])
                continue;
            cur[depth] = numbers[i];
            solution(cur, i, depth + 1);

        }
    }
}
