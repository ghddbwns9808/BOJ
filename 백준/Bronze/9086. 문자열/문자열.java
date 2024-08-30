import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        while (N --> 0){
            String line = br.readLine();
            bw.write(line.charAt(0) + "" + line.charAt(line.length()-1) + "\n");
        }
        bw.flush();
    }
}