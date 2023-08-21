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
    	//결과 값들을 담을 treeset(중복 x, 순서대로 정렬)
    	result = new TreeSet<>();
    	visited = new boolean[n];
    	cows = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	//아래 3줄은 조합 가능한 최대 숫자를 계산 -> 그 값만큼 에라토스테네스 채
    	Arrays.sort(cows);
    	int max = 0;
    	for(int i=1; i<=m; i++) 
    		max += cows[n-i];
    	
    	//소수 판단 배열 만들기
    	isPrimeNum = new boolean[max+1];
    	Arrays.fill(isPrimeNum, true);
    	makeEratosthenesSieve(max);
    	
    	//백트래킹 dfs 돌리기
    	dfs(m, 0, new Stack<Integer>());
    	
    	if(result.size() == 0)
    		System.out.println(-1);
    	else{
    		for(int e: result)
    			System.out.print(e + " ");
    	}
    } 
    
    //소수 판단 로직이 많으니 에라토스테네스의 채 만들어놓기
    private static void makeEratosthenesSieve(int maxNum) {
    	for(int i=2; i<=maxNum/2; i++) {
    		int j=2;
    		 
    		while(i*j <= maxNum) 
    			isPrimeNum[j++ * i] = false;
    	}
    }
    
    private static void dfs(int targetDepth, int curSum, Stack<Integer> cur) {
    	//m 만큼 사이즈가 되면 소수인지 판단후 result에 넣기
    	if(cur.size() == targetDepth) {
    		if(isPrimeNum[curSum])
    			result.add(curSum);
    		return;
    	}
    	//dfs 백트래킹
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