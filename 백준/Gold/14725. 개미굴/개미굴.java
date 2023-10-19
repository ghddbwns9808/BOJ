import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static ArrayList<Node> tree;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        tree.add(new Node(""));

        Node root = new Node("");

        while (N--> 0){
            Node cur = root;
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(tk.nextToken());

            for (int i=0; i<m; i++){
                String s = tk.nextToken();
                cur.insert(s);
                cur = cur.child.get(s);
            }
        }

        printTrie(root, 0);

        bw.flush();
    }

    private static void printTrie(Node cur, int depth) throws IOException {
        if (depth != 0){
            for (int i=0; i<2*(depth-1); i++)
                bw.write("-"+"");
            bw.write(cur.cur+"\n");
        }
        for (Map.Entry<String, Node> node: cur.child.entrySet()){
            printTrie(node.getValue(), depth+1);
        }
    }
}

class Node{
    Map<String, Node> child;
    String cur;

    public Node(String c){
        this.cur = c;
        this.child = new TreeMap<>();
    }

    public void insert(String s){
        child.putIfAbsent(s, new Node(s));
    }
}
