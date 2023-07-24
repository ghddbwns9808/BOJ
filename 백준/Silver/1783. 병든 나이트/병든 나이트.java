import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        //세로
        int n = Integer.parseInt(tk.nextToken());
        //가로
        int m = Integer.parseInt(tk.nextToken());

        if(n == 1 || m == 1)
            System.out.println(1);
        else if(n == 2){
            System.out.println(Math.min(4, (m+1) / 2));
        }
        else if (m < 7 )
            System.out.println(Math.min(4, m));
        else{
            System.out.println(m+5 - 7);
        }
    }
}
