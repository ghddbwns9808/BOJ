import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[] operators = {'+', '-', '*', '/'};
    static char[] operator;
    static boolean[] visited;
    static int[] numbers;
    static Set<String> possibleOperators = new HashSet<>();

    static int maxResult = Integer.MIN_VALUE;
    static int minResult = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        numbers = new int[n];

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            numbers[i] = Integer.parseInt(tk.nextToken());

        int[] opCnt = new int[4];
        tk = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++)
            opCnt[i] = Integer.parseInt(tk.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<4; i++){
            while (opCnt[i]-- > 0)
                sb.append(operators[i]);
        }
        operator = sb.toString().toCharArray();
        visited = new boolean[operator.length];

        dfs(new StringBuilder());
        calc();
        bw.write(maxResult + "\n");
        bw.write(minResult + "\n");

        //bw.write(line);
        bw.flush();
        bw.close();
    }

    private static void dfs(StringBuilder sb){
        //연산자를 모두 써서 조합
        if (sb.length() == operator.length){
            possibleOperators.add(sb.toString());
            return;
        }

        for (int i=0; i<operator.length; i++){
            if(!visited[i]){
                visited[i] = true;
                sb.append(operator[i]);
                dfs(sb);
                visited[i] = false;
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    private static void calc(){
        for(String op: possibleOperators){
            int tmp = numbers[0];
            for(int i=0; i<op.length(); i++){
                switch (op.charAt(i)){
                    case '+':
                        tmp += numbers[i+1];
                        break;
                    case '-':
                        tmp -= numbers[i+1];
                        break;
                    case '*':
                        tmp *= numbers[i+1];
                        break;
                    case '/':
                        tmp /= numbers[i+1];
                        break;
                }
            }
            maxResult = Math.max(maxResult, tmp);
            minResult = Math.min(minResult, tmp);
        }
    }
}