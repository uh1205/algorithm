class Solution {
    int solution(int[][] land) {
        int[] dp = new int[4];
        dp[0] = land[0][0];
        dp[1] = land[0][1];
        dp[2] = land[0][2];
        dp[3] = land[0][3];
        
        for (int i = 1; i < land.length; i++) {
            int dp0 = land[i][0] +
                Math.max(dp[1], Math.max(dp[2], dp[3]));
            int dp1 = land[i][1] +
                Math.max(dp[0], Math.max(dp[2], dp[3]));
            int dp2 = land[i][2] +
                Math.max(dp[0], Math.max(dp[1], dp[3]));
            int dp3 = land[i][3] +
                Math.max(dp[0], Math.max(dp[1], dp[2]));
            
            dp[0] = dp0;
            dp[1] = dp1;
            dp[2] = dp2;
            dp[3] = dp3;
        }
        
        return Math.max(dp[0], Math.max(dp[1], Math.max(dp[2], dp[3])));
    }
}