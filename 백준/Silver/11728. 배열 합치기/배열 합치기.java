import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tk = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(tk.nextToken());
        int m = Integer.parseInt(tk.nextToken());

        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] merged = new int[n+m];

        int idxA = 0;
        int idxB = 0;
        int idxM = 0;

        while (idxA<n || idxB<m){
            if (idxA == n){
                merged[idxM] = b[idxB];
                idxB++;
            } else if (idxB == m) {
                merged[idxM] = a[idxA];
                idxA++;
            }
            else if(a[idxA] <= b[idxB]){
                merged[idxM] = a[idxA];
                idxA++;
            }else{
                merged[idxM] = b[idxB];
                idxB++;
            }
            idxM++;
        }
        for(int num: merged){
            bw.write(num +" ");
        }
        bw.flush();
        bw.close();
    }
}
