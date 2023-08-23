import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static boolean[][] map;
    static int n, cnt;
    
    public static void main(String[] args) throws IOException {
    	n = Integer.parseInt(br.readLine());
    	map = new boolean[n][n];
    	cnt = 0;
    	
    	dfs(0);
    	System.out.println(cnt);
    } 
    
    private static void dfs(int rowNum) {
    	if(rowNum == n) {
    		cnt++;
    		return;
    	}
    	for(int i=0; i<n; i++) {
    		if(isValidate(rowNum, i)) {
    			map[rowNum][i] = true;
    			dfs(rowNum+1);
    			map[rowNum][i] = false;
    		}
    	}
    }
    
    private static boolean isValidate(int x, int y) {
    	return isColValidate(x, y) && isDiagValidate(x, y);
    }
    
    private static boolean isColValidate(int x, int y) {
    	for(int i=0; i<x; i++) {
    		if(map[i][y])
    			return false;
    	}
    	return true;
    }

    private static boolean isDiagValidate(int x, int y) {
    	int i=x;
    	int j=y;
    	while(i>=0 && j>=0) {
    		if(map[i--][j--])
    			return false;
    	}
    	while(x>=0 && y<n) {
    		if(map[x--][y++])
    			return false;
    	}
    	return true;
    }
    
    private static void print() {
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<n; j++)
    			System.out.print(map[i][j] + " ");
    		System.out.println();
    	}
    }
}