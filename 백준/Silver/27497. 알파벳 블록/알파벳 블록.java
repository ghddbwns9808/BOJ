import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine());
		Deque<String> dq = new ArrayDeque<>();
		Stack<Integer> s = new Stack<>();
		
		while(n-- > 0) {
			String[] command = br.readLine().split(" ");

			switch (Integer.parseInt(command[0])) {
			case 1:
				dq.addLast(command[1]);
				s.add(1);
				break;
			case 2:
				dq.addFirst(command[1]);
				s.add(2);
				break;
			case 3:
				if(s.isEmpty()) continue;
				
				int c = s.pop();
				if(c == 1) {
					dq.pollLast();
				}else {
					dq.pollFirst();
				}

			default:
				break;
			}
		}
		if (dq.isEmpty()) System.out.println(0);
		
		else {
			StringBuilder sb = new StringBuilder();
			while(!dq.isEmpty())
				sb.append(dq.pollFirst());
			System.out.println(sb);
		}	
	}
}