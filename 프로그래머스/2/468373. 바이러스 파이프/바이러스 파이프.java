import java.util.*;

class Solution {
    int n, k, ans = 0;
    List<List<int[]>> graph = new ArrayList<>();
    
    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.k = k;
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] e : edges) {
            int x = e[0], y = e[1], t = e[2];
            graph.get(x).add(new int[]{y, t});
            graph.get(y).add(new int[]{x, t});
        }
        
        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;
        
        dfs(0, 0, 1, infected);
        
        return ans;
    }
    
    void dfs(int depth, int type, int count, boolean[] infected) {
        ans = Math.max(ans, count);
        
        if (count == n || depth == k) {
            return;
        }
        
        for (int t = 1; t <= 3; t++) {
            if (t != type) {
                boolean[] newInfected = infected.clone();
                int newCount = bfs(t, newInfected);
                
                if (newCount > count) {
                    dfs(depth + 1, t, newCount, newInfected);
                }
            }
        }
    }
    
    int bfs(int type, boolean[] infected) {
        int count = 0;
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 1; i <= n; i++) {
            if (infected[i]) {
                q.offer(i);
                count++;
            }
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int[] e : graph.get(cur)) {
                int nn = e[0];
                int nt = e[1];
                if (nt == type && !infected[nn]) {
                    q.offer(nn);
                    infected[nn] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
}