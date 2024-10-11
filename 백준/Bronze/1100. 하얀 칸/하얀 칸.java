import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int ans = 0;
        for (int i=0; i<8; i++){
            String line = br.readLine();
            for (int j=0; j<8; j++){
                char c = line.charAt(j);
                if (c == 'F') {
                    if (i%2 == 0 && j%2 == 0)
                        ans++;

                    if (i%2 == 1 && j%2 == 1)
                        ans++;
                }
            }
        }

        bw.write(ans+"");
        bw.flush();
    }

}
