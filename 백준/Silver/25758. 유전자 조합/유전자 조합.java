import com.sun.source.util.Trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        //StringTokenizer tk = new StringTokenizer(br.readLine());
        String[] input = br.readLine().split(" ");
        //중복 방지 + 순서 정렬 해주는 자료구조
        Set<Character> answer = new TreeSet<>();

        //알파벳이 각각 첫번째 글자, 두번째 글자로 몇번 등장한지 확인하는 Map<알파벳, 등장횟수>
        Map<Character, Integer> firGene = new HashMap<>();
        Map<Character, Integer> secGene = new HashMap<>();

        //Map 2개에 알파벳 모두 넣어주기
        for(char c = 'A' ; c <='Z'; c++){
            firGene.put(c, 0);
            secGene.put(c, 0);
        }

        //input을 돌면서 firGene, secGene 등장 횟수 업데이트
        for(String gene: input){
            char f = gene.charAt(0);
            char s = gene.charAt(1);

            firGene.put(f, firGene.get(f)+1);
            secGene.put(s, secGene.get(s) + 1);
        }



        for(String gene: input){
            //2개의 map 순회를 위한 iterator
            Iterator firGeneIterator = firGene.keySet().iterator();
            Iterator secGeneIterator = secGene.keySet().iterator();

            char f = gene.charAt(0);
            char s = gene.charAt(1);

            while (secGeneIterator.hasNext()){
                char curSec = (char)secGeneIterator.next();
                //두번째 글자 map에서 f보다 사전적으로 작고 && 두번째 글자 map에 1회 이상 존재한다?
                //f를 2세대 유전자로 만들어줄 수 있는 character가 두번째 글자에 존재한다.
                if(f >= curSec && secGene.get(curSec) > 0){
                    //만약 존재 횟수가 1이다?
                    if(secGene.get(curSec) == 1){
                        //근데 그게 나의 두번째 글자다?
                        if(s == curSec) {
                            //그럼 추가할 수 없음
                            continue;
                        }
                    }
                    answer.add(f);
                    break;
                }
            }
            //같은 논리로 2번째 글자도 확인
            while (firGeneIterator.hasNext()){
                char curFir = (char)firGeneIterator.next();
                if(s >= curFir && firGene.get(curFir) > 0){
                    if(firGene.get(curFir) == 1){
                        if(f == curFir) {
                            continue;
                        }
                    }
                    answer.add(s);
                    break;
                }
            }
        }
        System.out.println(answer.size());
        StringBuilder sb = new StringBuilder();
        Iterator<Character> iterator = answer.iterator();
        while (iterator.hasNext())
            sb.append(iterator.next() + " ");
        System.out.println(sb);
    }
}
