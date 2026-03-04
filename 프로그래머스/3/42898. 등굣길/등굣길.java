class Solution {
    public int solution(int m, int n, int[][] puddles) {
        if (n == 1 || m == 1) return 1;
        
        int mod = 1_000_000_007;
        
        int[][] map = new int[n + 1][m + 1];
        
        for (int[] p : puddles) {
            map[p[1]][p[0]] = -1;
        }
        
        map[0][1] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 현재 위치가 물이면 패스
                if (map[i][j] == -1) {
                    continue;
                }
                map[i][j] = (map[i - 1][j] + map[i][j - 1]) % mod;
                // 위가 물이면 +1, 왼이 물이면 +1
                if (map[i - 1][j] == -1) {
                    map[i][j] = (map[i][j] + 1) % mod;
                }
                if (map[i][j - 1] == -1) {
                    map[i][j] = (map[i][j] + 1) % mod;
                }
            }
        }
        
        return map[n][m];
    }
}