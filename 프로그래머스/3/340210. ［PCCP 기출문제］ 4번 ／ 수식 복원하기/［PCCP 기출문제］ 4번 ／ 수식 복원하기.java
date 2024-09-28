import java.io.IOException;
import java.util.*;

class Solution {
    Set<Integer> rad = new HashSet<>();
    Set<String> operator = new HashSet<>();
    List<String> ambiguous = new ArrayList<>();

    public String[] solution(String[] expressions) {
        for (int i=2; i<10; i++)
            rad.add(i);

        operator.add("+");
        operator.add("-");
        operator.add("=");

        for (String exp: expressions){
            if (parseInput(exp)){
                ambiguous.add(exp);
            }else{
                calc(exp);
            }
        }

        String[] answer = new String[ambiguous.size()];

        for (String str: ambiguous)
            System.out.println(str);
        for (int i=0; i<ambiguous.size(); i++){
            answer[i] = calcAmbiguous(ambiguous.get(i));
        }

        return answer;
    }

    private boolean parseInput(String exp){
        boolean containX = false;
        StringTokenizer tk = new StringTokenizer(exp);

        while (tk.hasMoreTokens()){
            String str = tk.nextToken();
            if (operator.contains(str))
                continue;

            if (str.equals("X")) {
                containX = true;
                continue;
            }

            for (int i=0; i<str.length(); i++){
                int n = Character.getNumericValue(str.charAt(i));
                for (int j=2; j<=n; j++){
                    if (rad.contains(j))
                        rad.remove(j);
                }
            }
        }
        return containX;
    }

    private void calc(String exp){
        Set<Integer> removeRadix = new HashSet<>();

        for (int radix: rad){
            StringTokenizer tk = new StringTokenizer(exp);

            int num1 = Integer.parseInt(tk.nextToken(), radix);
            String op = tk.nextToken();
            int num2 = Integer.parseInt(tk.nextToken(), radix);
            tk.nextToken();
            int result = Integer.parseInt(tk.nextToken(), radix);

            if (op.equals("+")){
                if (num1 + num2 != result)
                    removeRadix.add(radix);
            }else{
                if (num1 - num2 != result)
                    removeRadix.add(radix);
            }
        }

        for (int rem: removeRadix)
            rad.remove(rem);
    }

    private String calcAmbiguous(String exp){
        String result = "";
        StringBuilder sb = new StringBuilder();
        StringTokenizer tmpTk = new StringTokenizer(exp, "=");
        sb.append(tmpTk.nextToken());
        sb.append("= ");


        for (int radix: rad){
            StringTokenizer tk = new StringTokenizer(exp);

            int num1 = Integer.parseInt(tk.nextToken(), radix);
            String op = tk.nextToken();
            int num2 = Integer.parseInt(tk.nextToken(), radix);

            tk.nextToken(); // =
            tk.nextToken(); // x

            String res;

            if (op.equals("+"))
                res = Integer.toString(num1+ num2, radix);
            else
                res = Integer.toString(num1-num2, radix);

            if (result.isEmpty()){
                result = res;
            }else{
                if (!result.equals(res)){
                    sb.append("?");
                    return sb.toString();
                }
            }
        }

        sb.append(result);
        return sb.toString();
    }
}