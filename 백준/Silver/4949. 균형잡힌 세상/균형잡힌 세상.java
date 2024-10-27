import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Set<Character> parentheses = new HashSet<>();
        parentheses.add('(');
        parentheses.add(')');
        parentheses.add('[');
        parentheses.add(']');

        while (true){
            String input = br.readLine();
            if (input.equals(".")){
                break;
            }

            Stack<Character> s = new Stack<>();
            boolean flag = false;
            for (int i=0; i<input.length(); i++){
                char c = input.charAt(i);
                if (parentheses.contains(c)){
                    if (c == '(' || c == '['){
                        s.push(c);
                    } else if (c == ')') {
                        if (s.isEmpty() || s.pop() != '('){
                            bw.write("no\n");
                            flag = true;
                            break;
                        }
                    } else if (c == ']') {
                        if (s.isEmpty() || s.pop() != '['){
                            bw.write("no\n");
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if (!flag){
                if (s.isEmpty()){
                    bw.write("yes\n");
                }else{
                    bw.write("no\n");
                }
            }
        }
        bw.flush();
    }


}