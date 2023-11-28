import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static double sum;
    static Point[] points;
    static Point start;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        sum = 0.0;
        points = new Point[N];
        for (int i=0; i<N; i++){
            StringTokenizer tk = new StringTokenizer(br.readLine());
            points[i] = new Point(Long.parseLong(tk.nextToken()), Long.parseLong(tk.nextToken()));
        }
        start = points[0];

        for (int i=1; i<N-1; i++)
            sum += calc(points[i], points[i+1]);
        String area = String.format("%.1f", Math.abs(sum));
        bw.write(area);
        bw.flush();
    }

    private static double calc(Point p1, Point p2){
        return ((p1.x - start.x)*(p2.y - start.y) - (p1.y- start.y)*(p2.x - start.x)) / 2.0;
    }
}

class Point {
    long x, y;

    public Point(long x, long y){
        this.x = x;
        this.y = y;
    }
}
