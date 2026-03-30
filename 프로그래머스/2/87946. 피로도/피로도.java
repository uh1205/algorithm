class Solution {
    int ans = 0;
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(0, k, dungeons);
        return ans;
    }
    
    void dfs(int depth, int left, int[][] dungeons) {
        ans = Math.max(ans, depth);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (visited[i] || left < dungeons[i][0]) continue;
            visited[i] = true;
            dfs(depth + 1, left - dungeons[i][1], dungeons);
            visited[i] = false;
        }
    }
}