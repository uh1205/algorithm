class Solution {
    int ans = 0;
    int n;
    int[][] dun;
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        dun = dungeons;
        visited = new boolean[n];
        dfs(0, k);
        return ans;
    }
    
    void dfs(int depth, int left) {
        ans = Math.max(ans, depth);
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || left < dun[i][0]) continue;
            visited[i] = true;
            dfs(depth + 1, left - dun[i][1]);
            visited[i] = false;
        }
    }
}