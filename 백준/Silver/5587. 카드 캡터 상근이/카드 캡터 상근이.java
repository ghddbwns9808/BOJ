import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static List<List<Integer>> people = new ArrayList<List<Integer>>();
    static int totalCard;
    //현재 턴: turn == 0 -> 상근이, turn == 1 -> 근상이
    static int turn = 0;
    public static void main(String[] args) throws IOException {
        init();

        //현재 게임 진행중인 카드 더미
        Stack<Integer> curCards = new Stack<>();
        //둘중 하나라도 
        while (people.get(0).size() != 0 && people.get(1).size() != 0){
            //현재 쌓인 카드가 하나도 없다?
            if(curCards.isEmpty()){
                //현재 턴인 사람 가장 작은카드 내기
                curCards.add(people.get(turn).remove(0));
                turn = (turn+1) % 2;
            }else{
                //가장 위에 있는 카드
                int lastCard = curCards.peek();
                //카드를 못냈는지 확인할 플래그 변수
                boolean cant = true;
                int idx = 0;

                //현재 턴인사람 카드를 앞에서 부터 탐색
                while (idx < people.get(turn).size()){
                    //지금 카드가 더미에서 가장 윗 카드보다 크다?
                    if(people.get(turn).get(idx) > lastCard){
                        //카드 내기
                        curCards.add(people.get(turn).remove(idx));
                        //플래그 변수 false로
                        cant = false;
                        break;
                    }
                    idx++;
                }
                //낼게 없었다?
                if (cant)
                    //스택 비우기
                    curCards.clear();
                //턴 변경
                turn = (turn + 1) % 2;
            }
        }
        //근상이 카드의 갯수 -> 상근이 점수
        System.out.println(people.get(1).size());
        System.out.println(people.get(0).size());

    }

    private static void init() throws IOException {
        totalCard = 2 * Integer.parseInt(br.readLine());
        //people[0]은 상근이 카드, people[1]은 근상이 카드
        people.add(new ArrayList<>());
        people.add(new ArrayList<>());
        //상근이 카드 입력 받기
        for(int i=0; i<totalCard/2; i++)
            people.get(0).add(Integer.parseInt(br.readLine()));
        //1 ~ 2n 중 상근이 카드에 없는 카드는 근상이 카드에 넣기
        for(int i=1; i<=totalCard; i++){
            if (!people.get(0).contains(i))
                people.get(1).add(i);
        }
        //두명 카드 다 정렬하기
        Collections.sort(people.get(0));
        Collections.sort(people.get(1));
    }
}