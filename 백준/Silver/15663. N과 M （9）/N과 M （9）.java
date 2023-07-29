import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
    private static int n, m;
    private static int[] nums, perm;
    private static boolean[] visited;
    private static LinkedHashSet<String> ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        nums = new int[n];
        perm = new int[m];
        visited = new boolean[n];
        ans = new LinkedHashSet<>();

        for (int i = 0; i < n; i++)
            nums[i] = sc.nextInt();

        Arrays.sort(nums);
        solution(0);
        ans.forEach(System.out::println);
    }

    static void solution(int cnt) {
        if (cnt == m) {
            StringBuilder sb = new StringBuilder();
            for (int p : perm)
                sb.append(p).append(' ');
            ans.add(sb.toString());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            perm[cnt] = nums[i];
            solution(cnt + 1);
            visited[i] = false;
        }
    }
}