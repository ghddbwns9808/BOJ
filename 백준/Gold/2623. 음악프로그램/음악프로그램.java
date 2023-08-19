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
        	String[] fd = br.readLine().split(" ");
            	
        	for(int i=1; i<fd.length-1; i++) {
        		int from = Integer.parseInt(fd[i]);
        		int to = Integer.parseInt(fd[i+1]);
        		
        		graph.get(from).add(to);
        		edges[to]++;
        	}
        }
            
        Queue<Integer> q = new LinkedList<Integer>();
            
        for(int i=1; i<=n; i++) {
            if(edges[i] == 0) {
            	q.add(i);
            	edges[i]--;
            }
           }
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            result.add(cur);
            
            for(int v: graph.get(cur)) 
            	edges[v]--;
            	
            for(int i=1; i<=n; i++) {
            	if(edges[i] == 0) {
            		edges[i]--;
            		q.add(i);
            	}
            }
        }
        if(result.size() != n)
        	bw.write('0');
        else {
        	for(int num: result) 
        		bw.write(num +"\n");
        }
        bw.flush();
    	bw.close();
    }
	
}