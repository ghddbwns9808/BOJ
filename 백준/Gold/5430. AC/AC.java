import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            //명령어
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());

            //deque에 숫자를 차례대로 넣음
            Deque<Integer> dq = new LinkedList<Integer>();
            String input = br.readLine();
            //양 끝 괄호 제거한 문자열
            input = input.substring(1, input.length() - 1);
            //n이 0이라 input이 ""이면 dq에 넣을 숫자 없음
            if (!input.equals("")){
                int[] inputArr = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
                for (int i = 0; i < n; i++)
                    dq.addLast(inputArr[i]);
            }

            //앞에서부터 뺄 것인가?
            boolean reversed = false;
            //중간에 에러로 종료했나?
            boolean err = false;
            //명령의 앞에서부터 차례대로 실행
            for (int i = 0; i < command.length(); i++) {
                /* 뒤집어라?
                 * -> 원소를 뺴는 방향을 바꿔라
                 */
                if (command.charAt(i) == 'R') {
                    if (dq.size() == 0) {
                        continue;
                    }
                    reversed = !reversed;
                } else {
                    if (dq.size() == 0) {
                        err = true;
                        break;
                    }
                    if (!reversed) dq.pollFirst();
                    else dq.pollLast();
                }
            }

            //중간에 에러 떴을 떄
            if (err) {
                System.out.println("error");
                continue;
            }

            //여기부터 출력
            System.out.print("[");
            //뒤집어져있으면 뒤에서부터 차례대로 poll
            if (reversed) {
                while (!dq.isEmpty()) {
                    //마지막 원소
                    if (dq.size() == 1) {
                        System.out.print(dq.pollLast() );
                        break;
                    }
                    System.out.print(dq.pollLast() + ",");
                }
            } else {
                while (!dq.isEmpty()) {
                    //마지막 원소
                    if (dq.size() == 1) {
                        System.out.print(dq.pollFirst());
                        break;
                    }
                    System.out.print(dq.pollFirst() + ",");
                }
            }
            System.out.println("]");
        }
    }
}
