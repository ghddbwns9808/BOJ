import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] board;
    static int n;
    static Shark s;
    
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        
        for(int i=0; i<n; i++) {
        	StringTokenizer tk = new StringTokenizer(br.readLine());
        	for(int j=0; j<n; j++) {
        		board[i][j] = Integer.parseInt(tk.nextToken());
        		if(board[i][j] == 9) {
        			s = new Shark(i, j);
        			board[i][j] = 0;
        		}
        	}
        }
        
        int time = 0;
        
        while(true) {
        	if(eatAllFishes() || cantEatMoreFish())
        		break;
//        	if(canEatButCantMove(s.x, s.y))
//        		break;
        	
        	Point nxtSharkPoint = nextTarget(new Point(s.x, s.y));
        	if(nxtSharkPoint.x == -1)
        		break;
        	time += nxtSharkPoint.dist;
        	
        	s.eatFish(nxtSharkPoint);
        }
        bw.write(time+"");
        bw.flush();
        bw.close();
    }

    //bfs로 가장 가까운 먹을 수 있는 물고기 찾기
    private static Point nextTarget(Point sharkPosition){
    	
    	boolean[][] visited = new boolean[n][n];
    	Queue<Point> q = new LinkedList<Point>();
    	List<Point> candidates = new ArrayList<Point>();
    	q.add(sharkPosition);
    	Point priorityPoint;
    	visited[s.x][s.y] = true;
    	
    	while(!q.isEmpty()) {
    		Point cur = q.poll();
    		
    		if(!candidates.isEmpty()) {
    			if(candidates.get(0).dist == cur.dist) {
    				priorityPoint = setPriorityPoint(candidates);
    				board[priorityPoint.x][priorityPoint.y] = 0;
    				return priorityPoint;
    			}
    		}
    		
    		for(int i=0; i<4; i++) {
    			int nx = cur.x + dx[i];
    			int ny = cur.y + dy[i];
    			int nDist = cur.dist + 1;
    			
    			if(isValidPoint(nx, ny) && !visited[nx][ny]) {
    				//먹음
    				if(board[nx][ny] != 0 && board[nx][ny] < s.size) {
    					visited[nx][ny] = true;
    					candidates.add(new Point(nx,  ny, nDist));
    				}
    				//위치의 값이 0 이거나 상어 사이즈랑 똑같을 때 q에 넣기
    				if(board[nx][ny] <= s.size) {
    					visited[nx][ny] = true; 
    					q.add(new Point(nx,  ny, nDist));
    				}
    			}
    		}
    	}
    	if(candidates.size() == 0)
    		return new Point(-1, -1);
    	priorityPoint = setPriorityPoint(candidates);
		board[priorityPoint.x][priorityPoint.y] = 0;
		return priorityPoint;
    	//return new Point(-1, -1);
    }
    
    private static Point setPriorityPoint(List<Point> candidates) {
    	Point priorityPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
    	for(Point p: candidates) {
    		if(priorityPoint.x > p.x) {
    			priorityPoint = p;
    			continue;
    		}else if (priorityPoint.x == p.x) {
    			if(priorityPoint.y > p.y) {
    				priorityPoint = p;
        			continue;
    			}
    		}
    	}
    	return priorityPoint;
    }
    
    private static boolean isValidPoint(int x, int y) {
    	return -1 < x && x<n && -1 < y && y < n;
    }
    
    private static boolean eatAllFishes() {
    	
    	int cnt=0;
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<n; j++) {
    			if(board[i][j] != 0) {
    				cnt++;
    				return false;
    			}
    				
    		}
    	}
    	//System.out.println("eat all");
    	return true;
    }
    
    private static boolean canEatButCantMove(int x, int y) {
    	for(int i=0; i<4; i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		if(isValidPoint(nx, ny)) {
	    		if(board[nx][ny] <= s.size) {
	    			//System.out.println("fuck");
	    			return false;
	    		}
    		}
    	}
    	return true;
    }
    
    private static boolean cantEatMoreFish() {
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<n; j++) {
    			if(board[i][j] != 0 && board[i][j] < s.size) {
    				
    				return false;
    			}
    		}
    	}
    	//System.out.println("cantEat");
    	return true;
    }
    
}

class Shark extends Point{
	int size;
	int needMoreFished;
	
	public Shark(int x, int y) {
		super(x, y);
		this.size = 2;
		this.needMoreFished = size;
	}
	
	private void getBigger() {
		if(needMoreFished == 0) {
			size++;
			needMoreFished = size;
		}
	}
	
	public void eatFish(Point fishPoint) {
		needMoreFished--;
		getBigger();
		
		this.x = fishPoint.x;
		this.y = fishPoint.y;
	}

}

class Point{
	int x;
	int y;
	int dist = 0;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, int dst) {
		this.x = x;
		this.y = y;
		this.dist = dst;
	}
}