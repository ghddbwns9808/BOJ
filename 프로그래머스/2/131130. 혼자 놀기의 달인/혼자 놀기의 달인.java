import java.util.*;
class Solution {
    int[] cards;
    boolean[] vst;
    PriorityQueue<Integer> point;

    public int solution(int[] cards) {
        this.cards = cards;
        for (int i=0; i<cards.length; i++)
            this.cards[i] = cards[i] - 1;

        vst = new boolean[cards.length];
        point = new PriorityQueue<>(Comparator.reverseOrder());

        pickBox();
        if (point.size() < 2)
            return 0;
        return point.poll() * point.poll();
    }

    private void pickBox(){
        for (int i=0; i<cards.length; i++){
            if (!vst[i]){
                int cnt = 0;
                int nxt = i;
                while (!vst[nxt]){
                    cnt++;
                    vst[nxt] = true;
                    nxt = cards[nxt];
                }
                point.offer(cnt);
            }
        }
    }
}
