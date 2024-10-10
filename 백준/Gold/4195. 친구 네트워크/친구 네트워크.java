import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int[] parent;
    static Map<String, Integer> mapping;
    static Map<Integer, Integer> friends;


    public static void main(String[] args) throws IOException {
        solve();
        bw.flush();
    }

    private static void solve() throws IOException{
        int T = Integer.parseInt(br.readLine());

        while (T-->0){
            mapping = new HashMap<>();
            friends = new HashMap<>();
            parent = new int[200001];
            N = Integer.parseInt(br.readLine());

            for (int i=0; i<parent.length; i++)
                parent[i] = i;

            while (N-->0){
                StringTokenizer tk = new StringTokenizer(br.readLine());
                String f1 = tk.nextToken();
                String f2 = tk.nextToken();

                if (!mapping.containsKey(f1)) {
                    mapping.put(f1, mapping.size()+1);
                    friends.put(mapping.get(f1), 1);
                }
                if (!mapping.containsKey(f2)) {
                    mapping.put(f2, mapping.size()+1);
                    friends.put(mapping.get(f2), 1);
                }

                bw.write(union(mapping.get(f1), mapping.get(f2)) + "\n");
            }
        }
    }

    private static int find(int a){
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    private static int union(int a, int b){
        int A = find(a);
        int B = find(b);

        /*
        if (A != B){
            if (A > B){
                parent[A] = B;
                friends.replace(B, friends.get(A) + friends.get(B));
            }else{
                parent[B] = A;
                friends.replace(A, friends.get(A) + friends.get(B));
            }
        }
         */

        if (A != B){
            parent[A] = B;
            friends.replace(B, friends.get(A) + friends.get(B));
        }

        return friends.get(B);
    }

}
