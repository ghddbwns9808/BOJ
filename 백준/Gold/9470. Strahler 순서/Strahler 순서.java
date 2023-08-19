import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		while(T--> 0) {
			StringTokenizer tk = new StringTokenizer(br.readLine());
			int tc = Integer.parseInt(tk.nextToken());
	        int n = Integer.parseInt(tk.nextToken());
	        int m = Integer.parseInt(tk.nextToken());
	        
	        Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
	        Map<Integer, ArrayList<Integer>> reverseGraph = new HashMap<Integer, ArrayList<Integer>>();
	        
	        int[] edges = new int[n+1];
	        int[] strahler = new int[n+1];
	        
	        for(int i=1; i<=n; i++) {
	        	graph.put(i, new ArrayList());
	        	reverseGraph.put(i, new ArrayList());
	        }
	            
	        while(m-->0) {
	        	tk = new StringTokenizer(br.readLine());
		        int from = Integer.parseInt(tk.nextToken());
	    		int to = Integer.parseInt(tk.nextToken());
	    		
	    		graph.get(from).add(to);
	    		reverseGraph.get(to).add(from);
	    		
	    		edges[to]++;
		    }
    		
	            
	        Queue<Integer> q = new LinkedList();
	            
	        for(int i=1; i<=n; i++) {
	            if(edges[i] == 0) {
	            	q.add(i);
	            	edges[i]--;
	            	strahler[i] = 1;
	            }
	        }
	        
	        while(!q.isEmpty()) {
	            int cur = q.poll();
	            
	            PriorityQueue<Integer> straList = new PriorityQueue<Integer>(Collections.reverseOrder());
	            for(int v: reverseGraph.get(cur))
	            	straList.offer(strahler[v]);
	            if(!straList.isEmpty()) {
		            int tmpStrahler = straList.poll();
		            if(!straList.isEmpty() && tmpStrahler== straList.peek())
		            	tmpStrahler++;
		            strahler[cur] = tmpStrahler;
	            }
	            
	            if(cur == n)
	            	break;
	            
	            for(int v: graph.get(cur)) 
	            	edges[v]--;
	            	
	            for(int i=1; i<=n; i++) {
	            	if(edges[i] == 0) {
	            		edges[i]--;
	            		q.add(i);
	            	}
	            }
	        }
	        bw.write(tc + " " + strahler[n] + "\n");
	        //System.out.println("end!");
//	        for(int num: strahler)
//	        	System.out.println(num);

		}
        bw.flush();
    	bw.close();
    }
}