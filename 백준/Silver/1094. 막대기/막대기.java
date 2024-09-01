import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int stick = 64;
        int cnt = 0;

        while (stick > 0){
            if (stick > N){
                stick /= 2;
                continue;
            }
            N -= stick;
            stick /= 2;
            cnt++;
        }
        bw.write(cnt+"");
        bw.flush();
    }
}