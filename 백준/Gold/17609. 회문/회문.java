import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String str;

    public static void main(String[] args) throws IOException {
        solve();
        bw.flush();
    }

    private static void solve() throws IOException{
        int T = Integer.parseInt(br.readLine());
        while (T --> 0){
            str = br.readLine();
            int len = str.length();

            int start = 0;
            int end = len-1;
            boolean isPalindrome = true;

            while (start < end){
                if (str.charAt(start) != str.charAt(end)){
                    isPalindrome = false;
                    if (isPseudoPalindrome(start+1, end)){
                        bw.write("1\n");
                    }else{
                        if (isPseudoPalindrome(start, end-1)){
                            bw.write("1\n");
                        }else{
                            bw.write("2\n");
                        }
                    }
                    break;
                }
                start++;
                end--;
            }
            if (isPalindrome)
                bw.write("0\n");
        }
    }

    private static boolean isPseudoPalindrome(int s, int e){
        int start = s;
        int end = e;

        while (start < end){
            if (str.charAt(start) != str.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }
}
