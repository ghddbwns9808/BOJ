import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder origin, target;
    public static void main(String[] args) throws IOException {
        origin = new StringBuilder(br.readLine());
        target = new StringBuilder(br.readLine());

        for (int i = target.length()-1; i >= origin.length(); i--){
            //마지막 글자 분리하기
            char lastChar = target.charAt(target.length()-1);
            target = new StringBuilder(target.deleteCharAt(target.length()-1));
            //마지막 글자가 B면 뒤집는다
            if (lastChar == 'B')
                target = target.reverse();
        }

        boolean canConvert = true;
        //target의 앞에서부터 origin size만큼의 배열이 origin과 같으면 가능한거임
        for (int i=0; i<origin.length(); i++){
            if (origin.charAt(i) != target.charAt(i)){
                canConvert = false;
                break;
            }
        }

        if (canConvert) bw.write("1");
        else bw.write("0");
        bw.flush();
    }
}
/*
1. ( 타겟 문자 길이 - 초기 문자 길이) 만큼 연산을 수행 해야 함
2. 2가지 연산 모두 마지막에 문자를 추가하는 것이기 때문에 타겟의 마지막 글자부터 만족시킬 수 있는 연산을 수행하면 된다
 */
