import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        StringTokenizer tk;
        int T = Integer.parseInt(br.readLine());

        for (int tc=0; tc<T; tc++){
            TreeMap<Integer, Integer> tree = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());

            for(int i=0; i<k; i++){
                tk = new StringTokenizer(br.readLine());
                String command = tk.nextToken();
                int num = Integer.parseInt(tk.nextToken());

                if (command .equals("I")){
                    tree.put(num, tree.getOrDefault(num, 0) + 1);
                } else if (tree.size() == 0) {
                    continue;
                } else{
                    if (num == 1){
                        if (tree.get(tree.lastKey()) == 1)
                            tree.remove(tree.lastKey());
                        else
                            tree.put(tree.lastKey(), tree.get(tree.lastKey()) - 1);
                    }else{
                        if (tree.get(tree.firstKey()) == 1)
                            tree.remove(tree.firstKey());
                        else
                            tree.put(tree.firstKey(), tree.get(tree.firstKey()) - 1);
                    }
                }
            }

            if (tree.isEmpty())
                bw.write("EMPTY\n");
            else
                bw.write(tree.lastKey() + " " + tree.firstKey() + "\n");

            bw.flush();
        }

        bw.close();

    }
}
