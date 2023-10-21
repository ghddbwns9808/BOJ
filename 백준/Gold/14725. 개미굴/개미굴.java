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

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        //root 노드 생성
        Node root = new Node("");

        while (N--> 0){
            Node cur = root;
            StringTokenizer tk = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(tk.nextToken());

            //입력순대로 차례대로 트라이에 넣기
            for (int i=0; i<m; i++){
                String s = tk.nextToken();
                cur.insert(s);
                cur = cur.child.get(s);
            }
        }

        printTrie(root, 0);

        bw.flush();
    }

    //문제 출력 조건이 전위표기 방식으로 출력하게 되어있음
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
        //사전순 정렬이니 TreeMap
        this.child = new TreeMap<>();
    }

    //트라이에 insert하는 함수
    //child map에 key가 s인 노드가 없다면 넣어라
    public void insert(String s){
        child.putIfAbsent(s, new Node(s));
    }
}
