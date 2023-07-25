import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][][] tomatoes;
    static int[] dx = new int[]{1, 0, -1, 0, 0, 0};
    static int[] dy = new int[]{0, -1, 0, 1, 0, 0};
    static int[] dz = new int[]{0, 0, 0, 0, 1, -1};

    static int m;
    static int n;
    static int h;

    static int[][][] days;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        m = Integer.parseInt(tk.nextToken());
        n = Integer.parseInt(tk.nextToken());
        h = Integer.parseInt(tk.nextToken());

        tomatoes = new int[h][n][m];
        days = new int[h][n][m];

        boolean anyZero = false;
        for(int i=0; i<h; i++){
            for (int j=0; j<n; j++){
                Arrays.fill(days[i][j], Integer.MAX_VALUE);
                int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int k=0; k<m; k++){
                    tomatoes[i][j][k] = input[k];
                    if (input[k] == 0)
                        anyZero = true;
                }
            }
        }
        if (!anyZero){
            System.out.println(0);
            return;
        }

        for(int i=0; i<h; i++){
            for (int j=0; j<n; j++){
                for (int k=0; k<m; k++){
                    if (tomatoes[i][j][k] == 1)
                        bfs(new Tomato(i, j, k, 0));
                }
            }
        }

        int maxDay = Integer.MIN_VALUE;
        for(int i=0; i<h; i++){
            for (int j=0; j<n; j++){
                for (int k=0; k<m; k++){
                    if (days[i][j][k] == Integer.MAX_VALUE && tomatoes[i][j][k] == 0){
                        System.out.println(-1);
                        return;
                    }else if(days[i][j][k] != Integer.MAX_VALUE && tomatoes[i][j][k] != -1)
                        maxDay = Math.max(maxDay, days[i][j][k]);
                }
            }
        }
        System.out.println(maxDay);

    }

    private static void bfs(Tomato start){
        Queue<Tomato> s = new LinkedList<>();
        s.offer(start);
        days[start.x][start.y][start.z] = start.day;

        while (!s.isEmpty()){
            Tomato cur = s.poll();

            for(int i=0; i<6; i++){
                int movedX = cur.x + dx[i];
                int movedY = cur.y + dy[i];
                int movedZ = cur.z + dz[i];

                if (isValidPosition(movedX, movedY, movedZ)){
                    if (tomatoes[movedX][movedY][movedZ] != -1 ){
                        if (days[movedX][movedY][movedZ] > cur.day + 1){
                            days[movedX][movedY][movedZ] = cur.day+1;
                            s.offer(new Tomato(movedX, movedY, movedZ, cur.day + 1));
                        }
                    }
                }
            }
        }
    }

    private static boolean isValidPosition(int x, int y, int z){
        return 0 <= x && x < h && 0 <= y && y < n && 0 <= z && z < m;
    }
}

class Tomato{
    int x;
    int y;
    int z;
    int day;

    public Tomato(int x, int y, int z, int d){
        this.x = x;
        this.y = y;
        this.z = z;
        this.day = d;
    }
}
