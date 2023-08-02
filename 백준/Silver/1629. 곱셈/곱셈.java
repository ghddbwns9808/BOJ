import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dp;
    static long a,b,c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk = new StringTokenizer(br.readLine());
        a = Long.parseLong(tk.nextToken());
        b = Long.parseLong(tk.nextToken());
        c = Long.parseLong(tk.nextToken());

        System.out.println(dc(b) );
    }

    private static long dc(long n){
        if (n == 1) return a % c;

        long dcRemain = dc(n/2)%c;

        if(n%2 == 0)
            return dcRemain*dcRemain%c;
        else
            return dcRemain*dcRemain%c * a%c;

    }
}
