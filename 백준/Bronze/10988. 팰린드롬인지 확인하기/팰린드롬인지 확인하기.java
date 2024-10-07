import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int ans;


    public static void main(String[] args) throws IOException {
        String word = br.readLine();
        int start = 0;
        int end = word.length()-1;

        while (start<end){
            if (word.charAt(start) != word.charAt(end)){
                System.out.println("0");
                return;
            }
            start++;
            end--;
        }
        System.out.println("1");
    }
}
