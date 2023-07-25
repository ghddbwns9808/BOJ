import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tk;

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		int[][] meeting = new int[n][2];
		int cnt = 0;
		
		PriorityQueue<Meeting> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i=0; i<n; i++) {
			tk = new StringTokenizer(br.readLine(), " ");
			pq.add(new Meeting(Integer.parseInt(tk.nextToken()), Integer.parseInt(tk.nextToken())));
		}
		 Meeting cur = pq.poll();

		 while(!pq.isEmpty()) {
			 Meeting next = pq.poll();

			 if(cur.endT > next.startT) {
				 if(cur.endT >= next.endT)
					 cur = next;
				 else
					 continue;
			 }
			 else {
				 cnt++;
				 cur = next;
			 }
		}
		cnt++;
		System.out.println(cnt);
	}
}

class Meeting implements Comparable<Meeting>{
	int startT;
	int endT;
	
	public Meeting(int start, int end) {
		this.startT = start;
		this.endT = end;
	}

	@Override
	public int compareTo(Meeting o) {
		if(this.startT < o.startT)
			return 1;
		else if (this.startT == o.startT) {
			if(this.endT <= o.endT)
				return 1;
			else
				return -1;
		}else {
			return -1;
		}
	}	
}
