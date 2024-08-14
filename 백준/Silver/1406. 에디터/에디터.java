import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static EditorLinkedList list;
    static String initial;

    public static void main(String[] args) throws IOException {
        input();
        solve();

        bw.write(list.answer() + "");
        bw.flush();
        bw.close();
    }

    static void input() throws IOException{
        list = new EditorLinkedList(br.readLine());
        M = Integer.parseInt(br.readLine());
    }

    static void solve() throws IOException {
        while (M-- > 0){
            StringTokenizer tk = new StringTokenizer(br.readLine());

            switch (tk.nextToken()){
                case "P":{
                    list.p(tk.nextToken().charAt(0));
                    break;
                } case "L":{
                    list.l();
                    break;
                } case "D":{
                    list.d();
                    break;
                } default:{
                    list.b();
                }
            }
        }
    }

    public static class EditorLinkedList<Character> extends LinkedList<Character>{
        int cursor;
        LinkedList<java.lang.Character> list;
        ListIterator<java.lang.Character> iterator;

        public EditorLinkedList(String s){
            cursor = s.length();
            list = new LinkedList<java.lang.Character>();
            for (int i=0; i<s.length(); i++)
                list.add(s.charAt(i));
            iterator = list.listIterator(s.length());
        }

        public void l(){
            if (iterator.hasPrevious())
                iterator.previous();
        }

        public void d(){
            if (iterator.hasNext())
                iterator.next();
        }

        public void b(){
            if (iterator.hasPrevious()){
                iterator.previous();
                iterator.remove();
            }
        }

        public void p(java.lang.Character c){
            iterator.add(c);
        }

        public String answer(){
            StringBuilder sb = new StringBuilder();
            for (java.lang.Character c: this.list)
                sb.append(c);
            return sb.toString();
        }
    }

}
