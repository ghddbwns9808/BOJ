import java.io.*;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> s = new Stack<>();
        while (n-->0){
            int a = Integer.parseInt(br.readLine());
            if (a == 0)
                s.pop();
            else
                s.push(a);
        }
        int ans = 0;
        while (!s.isEmpty())
            ans += s.pop();
        bw.write(ans + "");
        bw.flush();
    }


}