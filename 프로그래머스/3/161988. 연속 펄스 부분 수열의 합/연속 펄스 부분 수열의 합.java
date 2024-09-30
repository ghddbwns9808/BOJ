class Solution {
    int[] plusSequence, minusSequence;
    long[] plusDp, minusDp;
    int N;
    long ans;

    public long solution(int[] sequence) {
        if (sequence.length == 1)
            return Math.max(sequence[0], sequence[0] * -1);
        
        input(sequence);
        solve();
        return ans;
    }

    private void input(int[] sequence){
        N = sequence.length;
        ans = Long.MIN_VALUE;
        int plus = 1;
        int minus = -1;

        plusSequence = new int[N];
        minusSequence = new int[N];

        plusDp = new long[N];
        minusDp = new long[N];

        for (int i=0; i<N; i++){
            plusSequence[i] = sequence[i] * plus;
            minusSequence[i] =  sequence[i] * minus;

            plus *= -1;
            minus *= -1;
        }

        plusDp[0] = plusSequence[0];
        minusDp[0] = minusSequence[0];
    }

    private void solve(){
        for (int i=1; i<N; i++){
            plusDp[i] = Math.max(plusDp[i-1] + plusSequence[i], plusSequence[i]);
            minusDp[i] = Math.max(minusDp[i-1] + minusSequence[i], minusSequence[i]);

            ans = Math.max(plusDp[i], ans);
            ans = Math.max(minusDp[i], ans);
        }
    }
}