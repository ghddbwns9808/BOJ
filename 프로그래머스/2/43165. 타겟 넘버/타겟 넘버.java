class Solution {
    int ans;
    
    public int solution(int[] numbers, int target) {
        dfs(0, 0, target, numbers);
        return ans;
    }
    
    private void dfs(int idx, int cur, int target, int[] numbers){
        if(idx == numbers.length){
            if(cur == target)
                ans++;
            return;
        }
        
        dfs(idx+1, cur + numbers[idx], target, numbers);
        dfs(idx+1, cur - numbers[idx], target, numbers);
    }
}