import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, d, k, c, ans;
    static int[] sushi;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bw.write(ans + "");
        bw.flush();
    }

    private static void input() throws IOException{
        StringTokenizer tk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tk.nextToken());
        d = Integer.parseInt(tk.nextToken());
        k = Integer.parseInt(tk.nextToken());
        c = Integer.parseInt(tk.nextToken());

        sushi = new int[N];
        for (int i=0; i<N; i++)
            sushi[i] = Integer.parseInt(br.readLine());

        map.put(c, 1);
        for (int i=0; i<k; i++) {
            map.putIfAbsent(sushi[i], 0);
            map.replace(sushi[i], map.get(sushi[i]) + 1);
        }

        ans = map.size();
    }

    private static void solve(){
        for (int i=0; i<N; i++){
            if (map.get(sushi[i]) == 1)
                map.remove(sushi[i]);
            else
                map.replace(sushi[i], map.get(sushi[i]) - 1);

            map.putIfAbsent(sushi[(i+k) % N], 0);
            map.replace(sushi[(i+k) % N], map.get(sushi[(i+k) % N]) + 1);

            ans = Math.max(ans, map.size());
        }
    }
}