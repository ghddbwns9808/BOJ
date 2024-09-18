import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

       while (true){
           String input = br.readLine();
           int cnt = 0;
           if (input.length() == 1 && input.charAt(0) == '#') break;
           for (int i=0; i<input.length(); i++){
               if (set.contains(Character.toLowerCase(input.charAt(i))))
                   cnt++;
           }
           bw.write(cnt+"\n");
       }
       bw.flush();
    }
}