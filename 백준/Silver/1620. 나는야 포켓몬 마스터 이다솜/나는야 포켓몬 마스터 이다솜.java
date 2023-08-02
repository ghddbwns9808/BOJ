import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, String> numToName = new HashMap<>();
        Map<String, String> nameToNum = new HashMap<>();

        StringTokenizer tk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tk.nextToken());
        int m = Integer.parseInt(tk.nextToken());

        for(int i=1; i<=n; i++){
            String name = br.readLine();
            numToName.put(String.valueOf(i), name);
            nameToNum.put(name, String.valueOf(i));
        }

        while (m-->0){
            String question = br.readLine();
            if (numToName.containsKey(question)){
                bw.write(numToName.get(question) + "\n");
            }else{
                bw.write(nameToNum.get(question) + "\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
