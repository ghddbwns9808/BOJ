import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        String line = br.readLine();
        Set<String> cycleSet= new LinkedHashSet();
        cycleSet.add(line);
        
        for(int i=0; i<n; i++) {
        	line = changeLine(line);
        	if(cycleSet.contains(line)) {
        		n = n%(i + 1);
        		break;
        	}
        	cycleSet.add(line);
        }
        String[] resultSet = cycleSet.toArray(new String[0]);
        
        
        bw.write(resultSet[n]);
        bw.flush();
        bw.close();
    }

    private static String changeLine(String line){
        Queue<Character> front = new LinkedList<>();
        Stack<Character> rear = new Stack<>();
        StringBuilder newLine = new StringBuilder();

        for(int i=0; i<line.length(); i++){
            if(i%2 == 0)
                front.offer(line.charAt(i));
            else
                rear.add(line.charAt(i));
        }

        while (!front.isEmpty())
            newLine.append(front.poll());
        while (!rear.isEmpty())
            newLine.append(rear.pop());

        return newLine.toString();
    }
}
