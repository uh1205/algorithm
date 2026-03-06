class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans++;
                dfs(n, computers, i, visited);
            }
        }
        
        return ans;
    }
    
    void dfs(int n, int[][] computers, int cur, boolean[] visited) {
        visited[cur] = true;
        for (int next = 0; next < n; next++) {
            if (!visited[next] && next != cur && computers[cur][next] == 1) {
                dfs(n, computers, next, visited);
            }
        }
    }
}