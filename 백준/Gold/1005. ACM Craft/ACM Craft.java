import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws IOException {
    	int tc = Integer.parseInt(br.readLine());
    	while(tc-->0) {
    		StringTokenizer tk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(tk.nextToken());
            int m = Integer.parseInt(tk.nextToken());
            
            Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
            Map<Integer, ArrayList<Integer>> reversedGraph = new HashMap<Integer, ArrayList<Integer>>();
            
            int[] edges = new int[n+1];
            int[] dp = new int[n+1];
            int[] times = new int[n+1];
            tk = new StringTokenizer(br.readLine());
            
            for(int i=1; i<=n; i++) {
            	int time = Integer.parseInt(tk.nextToken());
            	times[i] = time;
            	dp[i] = time;
            }
            for(int i=1; i<=n; i++) {
            	graph.put(i, new ArrayList<>());
            	reversedGraph.put(i, new ArrayList<>());
            }
            
            while(m-->0) {
            	tk = new StringTokenizer(br.readLine());
            	
            	int a = Integer.parseInt(tk.nextToken());
            	int b = Integer.parseInt(tk.nextToken());
            
            	graph.get(a).add(b);
            	reversedGraph.get(b).add(a);
            	edges[b]++;
            }
            
            int target = Integer.parseInt(br.readLine());
            
            Queue<Integer> q = new LinkedList<Integer>();
            
            for(int i=1; i<=n; i++) {
            	if(edges[i] == 0) {
            		q.add(i);
            		edges[i]--;
            	}
            }
            
            while(!q.isEmpty()) {
            	int cur = q.poll();
            	
            	int tmp = 0;
            	for(int v: reversedGraph.get(cur)) 
            		tmp = Math.max(dp[v], tmp);
            	dp[cur] = tmp + times[cur];
      
            	for(int v: graph.get(cur)) 
            		edges[v]--;
            	
            	if(cur == target)
            		break;
            	
            	for(int i=1; i<=n; i++) {
            		if(edges[i] == 0) {
            			edges[i]--;
            			q.add(i);
            		}
            	}
            }
            bw.write(dp[target]+"\n");
    	}
    	bw.flush();
    	bw.close();
    } 
}