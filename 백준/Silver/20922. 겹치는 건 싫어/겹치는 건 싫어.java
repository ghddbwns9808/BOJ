import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[] numbers;
    static int n, k;
    public static void main(String[] args) throws IOException {
        StringTokenizer tk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tk.nextToken());
        k = Integer.parseInt(tk.nextToken());
        numbers = new int[n];
        Map<Integer, Integer> curNums = new HashMap<>();
        int curSize = 0;
        int maxLen = Integer.MIN_VALUE;

        tk = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++)
            numbers[i] = Integer.parseInt(tk.nextToken());

        int left = 0;
        int right = 0;

        while (right < n){
            if(!curNums.containsKey(numbers[right]))
                curNums.put(numbers[right], 1);

            else{
                if (curNums.get(numbers[right]) == k){
                    while (numbers[left] != numbers[right]){
                        curNums.replace(numbers[left], curNums.get(numbers[left])-1);
                        if (curNums.get(numbers[left]) == 0)
                            curNums.remove(numbers[left]);
                        curSize--;
                        left++;
                    }
                    left++;
                    curSize--;

                }
                else
                    curNums.replace(numbers[right], curNums.get(numbers[right]) + 1);
            }
            right++;
            curSize++;
            maxLen = Math.max(maxLen, curSize);
        }
        bw.write(maxLen+"");
        bw.flush();
    }
}