import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] isPrimeNum, visited;
    static int[] cows;
    static Set<Integer> result;
    static int n,m;
    
    public static void main(String[] args) throws IOException {
    	StringTokenizer tk = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(tk.nextToken());
    	m = Integer.parseInt(tk.nextToken());
    	result = new TreeSet<>();
    	visited = new boolean[n];
    	cows = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	Arrays.sort(cows);
    	
    	int max = 0;
    	for(int i=1; i<=m; i++) 
    		max += cows[n-i];
    	
    	isPrimeNum = new boolean[max+1];
    	Arrays.fill(isPrimeNum, true);
    	makeEratosthenesSieve(max);
    	dfs(m, 0, new Stack<Integer>());
    	if(result.size() == 0)
    		System.out.println(-1);
    	else{
    		for(int e: result)
    			System.out.print(e + " ");
    	}
    } 
    
    private static void makeEratosthenesSieve(int maxNum) {
    	for(int i=2; i<=maxNum/2; i++) {
    		int j=2;
    		 
    		while(i*j <= maxNum) 
    			isPrimeNum[j++ * i] = false;
    	}
    }
    
    private static void dfs(int targetDepth, int curSum, Stack<Integer> cur) {
    	if(cur.size() == targetDepth) {
    		if(isPrimeNum[curSum])
    			result.add(curSum);
    		return;
    	}
    	for(int i=0; i<n; i++) {
    		if(visited[i])
    			continue;
    		cur.push(cows[i]);
    		visited[i] = true;
    		dfs(targetDepth, curSum+cows[i] ,cur);
    		cur.pop();
    		visited[i] = false;
    	}
    }
}