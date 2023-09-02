import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int[][] map;
    static boolean[][] visited;
    static int n,L,R;
    
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    
    static boolean moved;
    
    public static void main(String[] args) throws IOException {
    	StringTokenizer tk = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(tk.nextToken());
    	L = Integer.parseInt(tk.nextToken());
    	R = Integer.parseInt(tk.nextToken());
    	
    	map = new int[n][n];
    	
    	//입력 받습니다
    	for(int i=0; i<n; i++) {
    		tk = new StringTokenizer(br.readLine());
    		for(int j=0; j<n; j++) 
    			map[i][j] = Integer.parseInt(tk.nextToken());
    	}
    	
    	int cnt = 0;
    	while(true) {
    		visited = new boolean[n][n];
            //인구 이동이 있었는지 체크하는 변수
    		moved = false;
    		
            //모든 국가를 방문하는 게 1 싸이클
    		for(int i=0; i<n; i++) {
        		for(int j=0; j<n; j++) {
        			if(!visited[i][j]) {
        				dfs(new Point(i, j));
        			}
        		}
        	}
            //움직이지 않았으면 종료합니다
    		if(!moved) 
    			break;
    		cnt++;
    	}
    	System.out.println(cnt);
    	
    } 
    
    private static void dfs(Point start) {
    	Stack<Point> s = new Stack<>();
    	s.add(start);
        //국경이 열릴 나라들을 담아둘 list
    	List<Point> countries = new ArrayList<Point>();
    	countries.add(start);
        //국경잉 열린 나라의 인구 수 합
    	int populSum= map[start.x][start.y];
    	visited[start.x][start.y] = true;
    	
    	while(!s.isEmpty()) {
    		Point cur = s.pop();
    		
    		for(int i=0; i<4; i++) {
    			int nx = cur.x + dx[i];
    			int ny = cur.y + dy[i];
    			
    			if(isValidPoint(nx, ny) && !visited[nx][ny]) {
                    //국경 열릴 수 있나 조건 확인
    				if(Math.abs(map[cur.x][cur.y] - map[nx][ny]) >= L 
    						&& Math.abs(map[cur.x][cur.y] - map[nx][ny]) <= R) {
    					Point c = new Point(nx, ny);
                        //list에 담아주기
    					countries.add(c);
    					s.push(c);
    					visited[nx][ny] = true;
                        
                        //인구 합 갱신
    					populSum += map[nx][ny];
                        //국경이 열렸으니 moved true로
    					moved = true;
    				}
    			}
    		}
    	}
    	movePeople(countries, populSum);
    }
    
    private static void movePeople(List<Point> countries, int populSum) {
        //이동된 인구 수 계산
    	int averPopul = (int) Math.floor(populSum / countries.size());
    	
        //list에 담겨있는 나라들 하나씩 꺼내면서 인구수 갱신
    	for(Point p: countries) {
    		map[p.x][p.y] = averPopul;
    	}
    }
    
    private static boolean isValidPoint(int x, int y) {
    	return 0<=x && x <n && 0<=y && y<n;
    }
     
}

class Point{
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}