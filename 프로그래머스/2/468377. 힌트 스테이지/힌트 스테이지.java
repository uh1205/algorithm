class Solution {
    public int solution(int[][] cost, int[][] hint) {
        int n = cost.length;
        int m = n - 1;                          // 번들 개수 (스테이지 1 ~ n-1)

        // 번들별로 "스테이지별 힌트권 장수"를 미리 집계
        int[][] tickets = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < hint[i].length; j++) {
                tickets[i][hint[i][j] - 1]++;
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << m); mask++) {
            int total = 0;
            int[] cnt = new int[n];
            for (int i = 0; i < m; i++) {
                if ((mask & (1 << i)) == 0) continue;
                total += hint[i][0];            // 번들 판매 가격
                for (int s = 0; s < n; s++) cnt[s] += tickets[i][s];
            }
            for (int s = 0; s < n; s++) {
                total += cost[s][Math.min(cnt[s], n - 1)];  // 한 스테이지 최대 n-1장
            }
            answer = Math.min(answer, total);
        }
        return answer;
    }
}