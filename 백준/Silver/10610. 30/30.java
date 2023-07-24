import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        System.out.println(greedy(br.readLine()));
    }

    private static String greedy(String initialNum){
        boolean containsZero = false;
        long sum = 0;
        Integer[] numArr = new Integer[initialNum.length()];

        for(int i=0; i<initialNum.length(); i++) {
            numArr[i] = initialNum.charAt(i) - '0';
            sum += numArr[i];
            if (numArr[i] == 0)
                containsZero = true;
        }
        if (!containsZero || sum % 3 != 0)
            return "-1";

        Arrays.sort(numArr, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (int n: numArr){
            sb.append(n);
        }
        return sb.toString();
    }
}
