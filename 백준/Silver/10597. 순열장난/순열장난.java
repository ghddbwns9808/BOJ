import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static char[] numbers;
    static int n;
    static boolean[] visited;
    static boolean flag = false;
    
    public static void main(String[] args) throws IOException {
    	numbers = br.readLine().toCharArray();
    	n = calcN(numbers.length);
    	
    	visited = new boolean[n+1];
    	visited[0] = true;
    	
    	
    	dfs(new Stack<>(), 0);
    } 
    
    private static int calcN(int l) {
    	if(l < 10)
    		return l;
    	else {
    		return 9 +(l-9)/2;
    	}
    }
    
    private static void dfs(Stack<Integer> curS, int curIdx) {
    	if(curS.size() == n) {
    		for(int num: curS)
    			System.out.print(num + " ");
    		flag = true;
    		return;
    	}
    	int num1 = Character.getNumericValue(numbers[curIdx]);
    	if(!visited[num1]) {
    		visited[num1] = true;
    		curS.push(num1);
    		dfs(curS, curIdx+1);
    		if(flag)
    			return;
    		visited[num1] = false;
    		curS.pop();
    	}
    	if(curIdx < numbers.length-1) {
    		int num2 = Integer.parseInt(String.valueOf(numbers[curIdx]) + String.valueOf(numbers[curIdx + 1]));
    		if(num2 <= n && !visited[num2]) {
    			visited[num2] = true;
        		curS.push(num2);
        		dfs(curS, curIdx+2);
        		if(flag)
        			return;
        		visited[num2] = false;
        		curS.pop();
        	}
    	}
    	return;
    }
}