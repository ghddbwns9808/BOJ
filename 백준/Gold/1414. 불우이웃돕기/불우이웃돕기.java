import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static List<Edge> edges = new ArrayList<>();
    static int v;

    static int[] parent;

    public static void main(String[] args) throws IOException {
        v = Integer.parseInt(br.readLine());
        int totalWeight = 0;

        for (int i=0; i<v; i++){
            String input = br.readLine();
            for (int j=0; j<v; j++){
                char weightChar = input.charAt(j);
                if (weightChar == '0')
                    continue;
                int weight;
                if (Character.isUpperCase(weightChar))
                    weight = weightChar - 'A' + 27;
                else
                    weight = weightChar - 'a' + 1;
                totalWeight += weight;
                edges.add(new Edge(i, j, weight));
            }
        }

        parent = new int[v];
        for (int i=0; i<v; i++)
            parent[i] = i;
        Collections.sort(edges);
        int sum = 0;
        for (Edge e: edges){
            if (find(e.from) != find(e.to)){
                sum += e.weight;
                union(e.from, e.to);
            }
        }
        for (int i=0; i<v; i++)
            find(i);
        int key = parent[0];
        boolean connected = true;
        for (int a: parent){
            if (key != a){
                connected = false;
            }
        }
        if (connected) bw.write(totalWeight - sum +"");
        else bw.write("-1");
        bw.flush();
    }

    private static int find(int a){
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b){
        int A = find(a);
        int B = find(b);

        if (A != B)
            parent[B] = A;
    }
}

class Edge implements Comparable<Edge>{
    int from;
    int to;
    int weight;

    public Edge(int f, int t, int w){
        this.from = f;
        this.to = t;
        this.weight = w;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }

    @Override
    public String toString(){
        return "E[" + from + ", " + to + ", " + weight + "]";
    }
}