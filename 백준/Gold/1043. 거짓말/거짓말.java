import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n,m;
    static int[] parent, people;

    static List<ArrayList<Integer>> party = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());
        parent = new int[n+1];
        boolean[] visited = new boolean[n+1];
        boolean[] knowTruth = new boolean[n+1];

        for(int i=1; i<=n; i++)
            parent[i] = i;

        tk = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(tk.nextToken());
        people = new int[k];

//        if(k > 0) {
//            people[0] = Integer.parseInt(tk.nextToken());
//
//            for(int i=1; i<k; i++) {
//                people[i] = Integer.parseInt(tk.nextToken());
//                parent[people[i]] = people[0];
//            }
//        }
        if(k>0){
            for(int i=0; i<k; i++){
                knowTruth[Integer.parseInt(tk.nextToken())] = true;
            }
        }

        for(int i=0; i<m; i++) {
            party.add(new ArrayList<>());

            tk = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(tk.nextToken());
            for(int j=0; j<p; j++)
                party.get(i).add(Integer.parseInt(tk.nextToken()));

            if(p == 1)
                continue;

            for(int j=0; j<p-1; j++) {
                union(party.get(i).get(j), party.get(i).get(j+1));
            }

        }
        

        if(k == 0) {
            bw.write(m+"");
            bw.flush();
            return;
        }
//        int l = m;
//        while(l --> 0) {
//            for(int i=0; i<party.size(); i++) {
//                List<Integer> list = party.get(i);
//                for(int j = 0; j<list.size()-1; j++) {
//                    union(list.get(j), list.get(j+1));
//                }
//            }
//        }

//        int key = parent[people[0]];
//        for(int i=0; i<party.size(); i++) {
//            List<Integer> list = party.get(i);
//            for(int j: list) {
//                if(parent[j] == key) {
//                    m--;
//                    break;
//                }
//            }
//        }

        for(int i=1; i<=n; i++){
            if (knowTruth[i] && !visited[i]){
                int key= find(i);
                for(int j=1; j<=n; j++){
                    if(!visited[j] && !knowTruth[j]){
                        if(key == find(j)){
                            knowTruth[j] = true;
                            visited[j] = true;
                        }
                    }
                }
            }
        }


        int cnt = 0;
        for(int i=0; i<party.size(); i++){
            boolean flag = false;
            List<Integer> pa = party.get(i);
            for(int j=0; j<pa.size(); j++){
                if (knowTruth[pa.get(j)])
                    flag = true;
            }
            if (!flag)
                cnt++;
        }



        bw.write(cnt+"");
        bw.flush();
    }

    private static void union(int a, int b){
        int A = find(a);
        int B = find(b);
        parent[B] = A;
    }

    private static int find(int a){
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }


}