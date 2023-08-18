import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tk.nextToken());
        int m = Integer.parseInt(tk.nextToken());
        
        Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
        int[] edges = new int[n+1];
        
        for(int i=1; i<=n; i++)
        	graph.put(i, new ArrayList<>());
        
        while(m-->0) {
        	tk = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(tk.nextToken());
        	int b = Integer.parseInt(tk.nextToken());
        
        	graph.get(a).add(b);
        	edges[b]++;
        }
        
        Queue<Integer> q = new LinkedList<Integer>();
        
        for(int i=1; i<=n; i++) {
        	if(edges[i] == 0) {
        		q.add(i);
        		edges[i]--;
        	}
        }
        
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	bw.write(cur + " ");
        	
        	for(int v: graph.get(cur)) {
        		edges[v]--;
        	}
        	
        	for(int i=1; i<=n; i++) {
        		if(edges[i] == 0) {
        			edges[i]--;
        			q.add(i);
        		}
        	}
        }
    	
        bw.flush();
        bw.close();
    }
}