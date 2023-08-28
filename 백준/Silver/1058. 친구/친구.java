import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static boolean[][] friends;
    static int n, maxFriend;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        friends = new boolean[n][n];
        maxFriend = Integer.MIN_VALUE;

        for(int i=0; i<n; i++){
            String input = br.readLine();
            for(int j=0; j<n; j++){
                if(input.charAt(j) == 'Y')
                    friends[i][j] = true;
            }
        }
        for(int i=0; i<n; i++){
            maxFriend = Math.max(dfs(i), maxFriend);
        }

        bw.write(maxFriend + "");
        bw.flush();
    }

    private static int dfs(int start){
        Stack<Integer> s = new Stack<>();
        s.push(start);
        boolean[] visited = new boolean[n];
        visited[start] = true;
        int cnt = 0;

        for(int i=0; i<n; i++){
            if(friends[start][i]){
                s.push(i);
                visited[i] = true;
                cnt++;
            }
        }
        while (!s.isEmpty()){
            int cur = s.pop();

            for(int i=0; i<n; i++){
                if(friends[cur][i] && !visited[i]){
                    visited[i] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }
}