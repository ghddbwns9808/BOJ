import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n,m, maxJ, minJ;
    static int[] jewelry;

    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        m = Integer.parseInt(tk.nextToken());

        maxJ = Integer.MIN_VALUE;
        minJ = Integer.MAX_VALUE;

        jewelry = new int[m];

        for(int i=0; i<m; i++) {
            jewelry[i] = Integer.parseInt(br.readLine());
            maxJ = Math.max(maxJ, jewelry[i]);
            minJ = Math.min(minJ, jewelry[i]);
        }

        if(m == n) {
            bw.write(maxJ+"");
            bw.flush();
            return;
        }

        bw.write(binarySearch()+"");
        bw.flush();
    }

    private static int binarySearch() {
        int start = 0;
        int end = maxJ;

        while(start < end) {
            int mid = (start + end) / 2;

            if(isPossible(mid))
                start = mid + 1;
            else
                end = mid;
        }
        return end;
    }

    private static boolean isPossible(int nJewel) {
        int cnt = 0;
        for(int i=0; i<m; i++) {
            int k = jewelry[i];
            while (k > 0){
                cnt++;
                k -= nJewel;
            }
        }
        return cnt > n;
    }
}