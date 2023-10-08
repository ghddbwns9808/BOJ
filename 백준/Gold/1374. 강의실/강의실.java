import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static Meeting[] meetings;
    static List<Stack<Meeting>> meetingMgr;
    static int[] meetingRooms = new int[100000];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        meetings = new Meeting[N];
//        meetingMgr = new ArrayList<>();
//        meetingMgr.add(new Stack<>());

        PriorityQueue<Meeting> pq = new PriorityQueue<>();

        StringTokenizer tk;
        for (int i=0; i<N; i++){
            tk = new StringTokenizer(br.readLine());
            tk.nextToken();
//            meetings[i] = new Meeting(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken()));
            pq.add(new Meeting(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken())));
        }

        //회의 끝나는 시간 기준 -> 같을 떄는 시작시간 기준으로 정렬
        //Arrays.sort(meetings);

        //첫 회의는 바로 넣기
        //meetingMgr.get(0).push(pq.poll());

        int cnt = 1;
        meetingRooms[0] = pq.poll().end;


        // 두번째 회의부터 마지막 회의까지 순회

        while (!pq.isEmpty()) {
            boolean isAdded = false;
            Meeting cur = pq.poll();
            for (int i=0; i<cnt; i++) {
                if (meetingRooms[i] <= cur.start) {
                    meetingRooms[i] = cur.end;
                    isAdded = true;
                    break;
                }
            }
            if (!isAdded) {
                meetingRooms[cnt++] = cur.end;
            }
        }
//        }
//        for (int i=1; i<N; i++){
//            boolean isAdded = false;
//            for (int j=0; j<meetingMgr.size(); j++){
//                if (meetingMgr.get(j).peek().end <= meetings[i].start){
//                    meetingMgr.get(j).add(meetings[i]);
//                    isAdded = true;
//                    break;
//                }
//            }
//            if (!isAdded){
//                Stack<Meeting> newStack = new Stack<>();
//                newStack.add(meetings[i]);
//                meetingMgr.add(newStack);
//            }
//        }

        //stack 사이즈 출력
        bw.write(cnt+"");
        bw.flush();
    }

}

class Meeting implements Comparable<Meeting>{
    int start;
    int end;

    public Meeting(int s, int e){
        this.start = s;
        this.end = e;
    }

    @Override
    public int compareTo(Meeting meeting) {
        if (this.start == meeting.start)
            return this.end - meeting.end;
        return this.start - meeting.start;
    }
}

/*
1. 회의를 끝나는 시간을 기준으로 정렬한다.
2. 현재 존재하는 모든 Stack<Meeting>을 순회하며 넣을 수 있는 스택을 찾는다
3. 모든 스택 top 회의가 내 시작 시간보다 크면 Stack List에 새로운 Stack을 하나 추가해서 넣는다.
3. List의 사이즈가 정답
 */
