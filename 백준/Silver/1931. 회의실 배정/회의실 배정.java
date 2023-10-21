import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Meeting> pq = new PriorityQueue<>();
        int endTime;

        StringTokenizer tk;
        for (int i=0; i<N; i++){
            tk = new StringTokenizer(br.readLine());
            pq.add(new Meeting(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken())));
        }

        int cnt = 1;
        //첫 회의는 바로 넣기
        endTime = pq.poll().end;

        // 두번째 회의부터 마지막 회의까지 순회
        while (!pq.isEmpty()){
            Meeting cur = pq.poll();
            if (cur.start >= endTime){
                endTime = cur.end;
                cnt++;
            }
        }

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
        if (this.end == meeting.end)
            return this.start - meeting.start;
        return this.end - meeting.end;
    }
}

/*
1. 회의를 끝나는 시간을 기준으로 정렬한다.
2. 순서대로 스택에 넣는다
2-1 스택 top 회의가 내 시작 시간보다 크면 넣지 않는다
3. 스택의 사이즈가 정답
 */
