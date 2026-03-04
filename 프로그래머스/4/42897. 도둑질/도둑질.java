class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        int[] dp1 = new int[n]; // 0번 집을 터는 경우
        int[] dp2 = new int[n]; // 0번 집을 안 터는 경우
        
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0], money[1]);
        
        for (int i = 2; i < n - 1; i++) {
            // i번 집을 터는 경우, 안 터는 경우 비교
            dp1[i] = Math.max(dp1[i - 2] + money[i], dp1[i - 1]);
        }
        
        dp2[1] = money[1];
        
        for (int i = 2; i < n; i++) {
            // i번 집을 터는 경우, 안 터는 경우 비교
            dp2[i] = Math.max(dp2[i - 2] + money[i], dp2[i - 1]);
        }
        
        return Math.max(dp1[n - 2], dp2[n - 1]);
    }
}