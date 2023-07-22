import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static char[][] map;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int depth = (int) (Math.log(n/3) / Math.log(2));

        map = new char[n][2*n];

        for(int i=0; i<n; i++)
            Arrays.fill(map[i], ' ');

        draw(0, n-1, depth);

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<map.length; i++){
            for (int j=0; j<map[0].length; j++){
                sb.append(map[i][j]);
            }sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void draw(int startX, int startY, int depth){
        if (depth == 0){
            drawbase(startX, startY);
        }else{
            int tmp = 3* (int)Math.pow(2, depth-1);
            draw(startX, startY, depth-1);

            draw(startX + tmp, startY - tmp, depth-1);

            draw(startX + tmp, startY + tmp, depth-1);
        }
    }

    private static void drawbase(int startX, int startY){
        map[startX][startY] = '*';

        map[startX+1][startY-1] = '*';
        map[startX+1][startY+1] = '*';

        map[startX+2][startY-2] = '*';
        map[startX+2][startY-1] = '*';
        map[startX+2][startY] = '*';
        map[startX+2][startY+1] = '*';
        map[startX+2][startY+2] = '*';
    }
}