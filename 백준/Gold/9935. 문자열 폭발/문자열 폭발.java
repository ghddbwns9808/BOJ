import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String key = br.readLine();

        Stack<Character> s = new Stack<>();

        for(int i=0; i<line.length(); i++){
            s.push(line.charAt(i));

            if (s.size() >= key.length()){
                boolean containKey = true;
                for(int j=0; j<key.length(); j++){
                    if (s.get(s.size() - key.length() + j) != key.charAt(j)){
                        containKey = false;
                        break;
                    }
                }
                if (containKey){
                    for(int j=0; j<key.length(); j++)
                        s.pop();
                }
            }
        }
        if (s.isEmpty())
            System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            Iterator<Character> iterator  = s.iterator();
            while (iterator.hasNext()){
                sb.append(iterator.next());
            }
            System.out.println(sb);
        }
    }

}
