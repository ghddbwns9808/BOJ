import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, possibleBudget;
    static int[] budgets;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        budgets = new int[n];
        int budgetSum = 0;
        int minBudget = Integer.MAX_VALUE;
        int maxBudget = Integer.MIN_VALUE;

        StringTokenizer tk = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            budgets[i] = Integer.parseInt(tk.nextToken());
            budgetSum += budgets[i];
            minBudget = Math.min(minBudget, budgets[i]);
            maxBudget = Math.max(maxBudget, budgets[i]);
        }

        possibleBudget = Integer.parseInt(br.readLine());

        //필요로 하는 예산 총합이 가용예산보다 작으면 가장 큰 예산을 출력 후 종료
        if(possibleBudget >= budgetSum) {
            bw.write(maxBudget + "");
            bw.flush();
            return;
        }
        //가장 적은 예산 * 도시 수가 가용예산보다 크면 가용예산/도시수 출력 후 종료
        if(minBudget*n >= possibleBudget){
            bw.write((int) possibleBudget/n + "");
            bw.flush();
            return;
        }

        bw.write(binarySearch(minBudget, maxBudget) + "");
        bw.flush();
    }

    private static int binarySearch(int minBudget, int maxBudget) {
        int left = minBudget;
        int right = maxBudget;
        int mid;

        while (left < right){
            mid = (left + right) / 2;
            if (possibleBudget >= isPossible(mid))
                left = mid + 1;
            else
                right = mid;
        }
        return right-1;
    }

    private static int isPossible(int b){
        int tmpBudget = 0;
        for(int budget: budgets)
            tmpBudget += Math.min(budget, b);
        
        return tmpBudget;
    }
}