import org.w3c.dom.xpath.XPathResult;

import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Node root;

    public static void main(String[] args) throws IOException {
        input();
        postOrder(root);

        bw.flush();
    }

    private static void input() throws IOException{
        root = new Node(Integer.parseInt(br.readLine()));

        while (true){
            try {
                root.insert(Integer.parseInt(br.readLine()));
            }catch (Exception e){
                break;
            }
        }
    }

    private static void postOrder(Node node) throws IOException {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        bw.write(node.n+"\n");
    }
}

class Node{
    int n;
    Node left = null;
    Node right = null;

    public Node(int n){
        this.n = n;
    }

    void insert(int num){
        if (num < n){
            if (this.left == null)
                this.left = new Node(num);
            else this.left.insert(num);
        }else{
            if (this.right == null)
                this.right = new Node(num);
            else this.right.insert(num);
        }
    }
}