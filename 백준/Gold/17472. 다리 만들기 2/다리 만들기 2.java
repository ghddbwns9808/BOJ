
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] originalMap, map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] parent;
    static int n, m, islandCnt;
    static List<Bridge> bridges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());

        originalMap = new int[n][m];
        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            tk = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++){
                originalMap[i][j] = Integer.parseInt(tk.nextToken());
            }
        }

        makeMap();
        findHorizontalBridges();
        findVerticalBridges();

        /*print map
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        */

        /* print bridges
        for (Bridge b: bridges)
            System.out.println(b);
         */

        parent = new int[islandCnt+1];
        for (int i=1; i<=islandCnt; i++)
            parent[i] = i;

        Collections.sort(bridges);
        int lenSum = 0;

        for (Bridge b: bridges){
            if (find(b.from) != find(b.to)){
                 lenSum += b.len;
                 union(b.from, b.to);
            }
        }

        for (int i=0; i<=islandCnt; i++)
            find(i);

        /* print parent
        for (int a: parent)
            System.out.print(a + " ");
        System.out.println();
         */

        int key = parent[1];
        boolean canMake = true;
        for (int i=2; i<=islandCnt; i++) {
            if (key != parent[i])
                canMake =false;
        }
        if (canMake) bw.write(lenSum + "");
        else bw.write(-1+"");
        bw.flush();
    }

    private static void makeMap(){
        islandCnt = 0;
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (originalMap[i][j] == 1 && !visited[i][j]){
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                    map[i][j] = ++islandCnt;

                    while (!q.isEmpty()){
                        int[] cur = q.poll();

                        for (int k=0; k<4; k++){
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];

                            if (nx >=0 && nx<n && ny>=0 && ny<m){
                                if (!visited[nx][ny] && originalMap[nx][ny] == 1){
                                    visited[nx][ny] = true;
                                    map[nx][ny] = islandCnt;
                                    q.offer(new int[]{nx, ny});
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void findVerticalBridges(){
        visited = new boolean[n][m];
        for (int i=0; i<n-3; i++){
            for (int j=0; j<m; j++){
                int k = i;
                if (map[k][j] != 0 && !visited[k][j]){
                    while (k<n-1 && map[k+1][j] != 0){
                        k++;
                        visited[k][j] = true;
                    }
                    if (k == n-1) continue;

                    int l = k;
                    while (l<n-1 && map[l+1][j] == 0) l++;
                    if (l == n-1 || l-k == 1) continue;
                    bridges.add(new Bridge(map[i][j], map[l+1][j], l-k));
                }
            }
        }
    }

    private static void findHorizontalBridges(){
        visited = new boolean[n][m];
        for (int i=0; i<n; i++){
            for (int j=0; j<m-3; j++){
                if (map[i][j] != 0 && !visited[i][j]){
                    int k = j;
                    while (k<m-1 && map[i][k+1] != 0){
                        k++;
                        visited[i][k] = true;
                    }
                    if (k == m-1) continue;

                    int l = k;
                    while (l<m-1 && map[i][l+1] == 0) l++;
                    if (l == m-1 || l-k == 1) continue;
                    bridges.add(new Bridge(map[i][j], map[i][l+1], l-k));
                }
            }
        }
    }

    private static int find(int a){
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b){
        int A = find(a);
        int B = find(b);

        if (A != B)
            parent[B] = A;
    }
}

class Bridge implements Comparable<Bridge>{
    int from;
    int to;
    int len;

    public Bridge(int f, int t, int l){
        this.from = f;
        this.to = t;
        this.len = l;
    }

    @Override
    public int compareTo(Bridge bridge) {
        return this.len - bridge.len;
    }

    @Override
    public String toString(){
        return "B:[ " + from + " -> " + to + ", " + len +"]";
    }
}