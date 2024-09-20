import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while (true){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            String name = tk.nextToken();
            int age = Integer.parseInt(tk.nextToken());
            int weight = Integer.parseInt(tk.nextToken());

            if (name.equals("#") && age == 0 && weight == 0)
                break;

            if (age > 17 || weight>=80)
                bw.write(name + " Senior\n");
            else bw.write(name + " Junior\n");
        }
        bw.flush();
    }
}
