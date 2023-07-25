import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String input = br.readLine();

        int cnt = 0;
        String key = "IO".repeat(n) + "I";
        
        for(int i=0; i < m-key.length() + 1; i++){
            if (input.substring(i, i+key.length()).equals(key))
                cnt++;
        }
        System.out.println(cnt);
    }
}