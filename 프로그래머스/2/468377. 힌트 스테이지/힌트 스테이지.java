class Solution {
    public int solution(int[][] cost, int[][] hint) {
        int n = cost.length; // 스테이지 수
        int m = n - 1; // 번들 수
        
        // 번들별로 '스테이지별 힌트권 수' 미리 계산
        int[][] tickets = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < hint[i].length; j++) {
                tickets[i][hint[i][j] - 1]++;
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        // 모든 경우의 수
        for (int mask = 0; mask < (1 << m); mask++) { // 각 번들을 샀는가
            // 각 경우의 총 비용, 스테이지별 힌트권 수
            int total = 0;
            int[] cnt = new int[n];
            
            // 경우에 따른 번들 구매
            for (int i = 0; i < m; i++) {
                // 안 샀으면 패스
                if ((mask & (1 << i)) == 0) continue;
                // 샀으면 비용 지불, 힌트권 카운팅
                total += hint[i][0];
                for (int j = 0; j < n; j++) {
                    cnt[j] += tickets[i][j];
                }
            }
            
            // 전체 스테이지 비용 계산
            for (int i = 0; i < n; i++) {
                total += cost[i][Math.min(n - 1, cnt[i])];
            }
            
            ans = Math.min(ans, total);
        }
        
        return ans;
    }
}