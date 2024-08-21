import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        String ans = br.readLine().length() >= br.readLine().length() ? "go" : "no";
        System.out.println(ans);
    }
}