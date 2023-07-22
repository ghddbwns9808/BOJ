import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long cnt = 0L;
    static int[] notSorted;
    static int[] sorted;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        notSorted = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sorted = new int[n];
        merge_sort(0, n-1);
        System.out.println(cnt);

    }

    private static void merge_sort(int start, int end){
        if (start < end){
            int mid = (start + end) / 2;
            merge_sort(start, mid);
            merge_sort(mid+1, end);
            int p = start;
            int q = mid+1;
            int sortedIdx = start;

            while (p <= mid || q <= end){
                if (p > mid){
                    sorted[sortedIdx] = notSorted[q];
                    sortedIdx++; q++;
                }else if(q > end || notSorted[p] <= notSorted[q]){
                    sorted[sortedIdx] = notSorted[p];
                    sortedIdx++; p++;
                } else {
                    sorted[sortedIdx] = notSorted[q];
                    sortedIdx++; q++;
                    cnt += (mid+1) - p;
                }
            }
            for(int i=start; i<=end; i++)
                notSorted[i] = sorted[i];

        }
    }
}
