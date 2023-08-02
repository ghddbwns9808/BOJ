import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] map;
    static Queue<Command> dirQ = new LinkedList<Command>();
    static Deque<Point> snake;
    static int n;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int curDir = 0;
    static int remainApple;

    public static void main(String[] args) throws NumberFormatException, IOException {
        n = Integer.parseInt(br.readLine());
        map = new boolean[n+1][n+1];

        remainApple = Integer.parseInt(br.readLine());
        for(int i=0; i<remainApple; i++) {
            StringTokenizer tk = new StringTokenizer(br.readLine());
            map[Integer.parseInt(tk.nextToken())][Integer.parseInt(tk.nextToken())] = true;
        }

        int l = Integer.parseInt(br.readLine());
        while(l-->0) {
            StringTokenizer tk = new StringTokenizer(br.readLine());
            dirQ.add(new Command(Integer.parseInt(tk.nextToken()), tk.nextToken().charAt(0)));
        }
        System.out.println(startGame());
    }

    private static int startGame() {
        int curTime = 0;
        snake = new LinkedList<>();
        snake.add(new Point(1, 1));
        while(true) {
            curTime++;
            //이전 시간의 뱀의 머리
            Point head = snake.peekFirst();
            //이번 시간에 머리가 위치한 Point
            Point nextHead = new Point(head.x + dx[curDir], head.y + dy[curDir]);
            snake.addFirst(nextHead);
            //뱀 머리가 벽에 부딫히나?
            if(!isValidatePosition(nextHead) || collideWithBody(nextHead))
                return curTime;

            //사과 먹음
            if(map[nextHead.x][nextHead.y]) {
                remainApple--;
                map[nextHead.x][nextHead.y] = false;
            }
            else snake.pollLast();


            if(dirQ.size()>0 && dirQ.peek().sec == curTime) {
                Command c = dirQ.poll();
                if(c.dir == 'D')
                    curDir = (curDir+1)%4;
                else {
                    if(curDir == 0)
                        curDir = 4;
                    curDir--;
                }
            }
        }
    }

    private static boolean isValidatePosition(Point p) {
        if(0<p.x && p.x<=n && 0<p.y && p.y<=n)
            return true;
        return false;
    }

    private static boolean collideWithBody(Point head) {
        int collide = 0;
        for(Point body: snake) {
            if(head.x == body.x && head.y == body.y)
                collide++;
            if(collide == 2)
                return true;
        }
        return false;
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

class Command{
    int sec;
    char dir;

    public Command(int s, char d) {
        this.sec = s;
        this.dir = d;
    }
}