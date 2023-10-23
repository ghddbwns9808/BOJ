import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static Node root;
    static PriorityQueue<PhoneNumber> pq;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while(T --> 0){
            boolean isConsistent = true;
            root = new Node(-1, false);
            N = Integer.parseInt(br.readLine());
            pq = new PriorityQueue<>();

            for(int i=0; i<N; i++)
                pq.add(new PhoneNumber(br.readLine()));

            while (!pq.isEmpty()){
                String phoneNum = pq.poll().number;
                Node cur = root;

                for (int i=0; i<phoneNum.length(); i++) {
//                    System.out.println("cur " + cur.n);
                    if (phoneNum.charAt(i) != ' ') {
                        boolean isEndNum = i == phoneNum.length() - 1;
                        if (!cur.insert(phoneNum.charAt(i) - '0', isEndNum)) {
//                            System.out.println(i+"!!");
                            isConsistent = false;
                            pq.clear();
                            break;
                        }
                        cur = cur.child.get(phoneNum.charAt(i) - '0');

//                        System.out.println(cur.child.size()+",");
                    }
                }
            }
            if (isConsistent) bw.write("YES\n");
            else bw.write("NO\n");
        }

        bw.flush();
    }

}

class Node{
    int n;
    Map<Integer, Node> child;
    boolean isEnd;

    public Node(int n, boolean isE){
        this.n = n;
        this.child = new HashMap();
        this.isEnd = isE;
    }



    public boolean insert(int n, boolean isEndOfNum){
//        System.out.print("insert: " + n + " " + isEndOfNum + "->");
        //키 값이 존재하는 경우
        if(child.putIfAbsent(n, new Node(n, isEndOfNum)) != null){
//            System.out.println("이미 존재 "+!(this.child.get(n).isEnd|| isEndOfNum));
            return !this.child.get(n).isEnd ;
        }
        //키값이 존재하지 않는 경우
        else{
            return true;
        }
    }
}

class PhoneNumber implements Comparable<PhoneNumber>{
    String number;
    public PhoneNumber(String s){
        this.number = s;
    }

    @Override
    public int compareTo(PhoneNumber p) {
        if (this.number.length() == p.number.length()){
            return this.number.compareTo(p.number);
        }
        return this.number.length() - p.number.length();
    }
}
/*
1. 트라이 기본 문제입니다
 */