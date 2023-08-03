import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Stack<String> resultS = new Stack<>();
	static Stack<String> operatorS = new Stack<>();
	
	static Map<String, Integer> operatorPriority = new HashMap<String, Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		operatorPriority.put("+", 1);
		operatorPriority.put("-", 1);
		operatorPriority.put("*", 2);
		operatorPriority.put("/", 2);
		operatorPriority.put("(", 3);
		operatorPriority.put(")", 3);
		
		String input = br.readLine();
		
		for(int i=0; i<input.length(); i++) {
			String cur = input.substring(i, i+1);
			
			// cur가 연산자다
			if(operatorPriority.containsKey(cur)) {
				//연산자 스택이 비어있다?
				if(operatorS.isEmpty()) {
					operatorS.add(cur);
				}else {
					if(cur.equals("("))
						operatorS.add(cur);
					else if(cur.equals(")")){
						while(!operatorS.peek().equals("(")) {
							String a = resultS.pop();
							String b = resultS.pop();
							
							String op = operatorS.pop();
						
							resultS.add(b+a+op);
						}
						operatorS.pop();
					}
					//괄호가 아닌 연산자
					else {
						//스택 가장 위 연산자가 cur보다 우선순위가 높거나 같으면? (= cur보다 우선순위가 낮아질 때 까지 stack pop)
						while(operatorS.size() > 0 && !operatorS.peek().equals("(") && operatorPriority.get(operatorS.peek()) >= operatorPriority.get(cur)) {
							String a = resultS.pop();
							String b = resultS.pop();
							
							String op = operatorS.pop();
							
							resultS.add(b+a+op);
						}
						operatorS.add(cur);
					}
				}
			}
			//cur가 피연산자다 -> result Stack에 넣기
			else {
				resultS.add(cur);
			}
		}
		
		while(!operatorS.isEmpty()) {
			String a = resultS.pop();
			String b = resultS.pop();
			
			String op = operatorS.pop();
			
			resultS.add(b+a+op);
		}
		
		StringBuilder sb = new StringBuilder();
		Iterator<String> iterator = resultS.iterator();
		while (iterator.hasNext()) {
			sb.append(iterator.next());
		}
		System.out.println(sb);
		
	}	
	
}
