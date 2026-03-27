import java.util.*;

class Solution {
    int n, start, k, ans = 0;
    List<List<Edge>> graph = new ArrayList<>();
    
    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        start = infection;
        this.k = k;
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] e : edges) {
            int x = e[0], y = e[1], t = e[2];
            graph.get(x).add(new Edge(y, t));
            graph.get(y).add(new Edge(x, t));
        }
        
        dfs(new int[k], 0, 0);
        
        return ans;
    }
    
    void dfs(int[] pipes, int cur, int depth) {
        if (depth == k) {
            bfs(pipes);
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (cur != i) {
                pipes[depth] = i;
                dfs(pipes, i, depth + 1);
            }
        }
    }
    
    void bfs(int[] pipes) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] infected = new boolean[n + 1];
        infected[start] = true;
        
        int count = 1;
        
        for (int pipe : pipes) {
            for (int i = 1; i <= n; i++) {
                if (infected[i]) q.offer(i);
            }
            
            while (!q.isEmpty()) {
                int cur = q.poll();
                
                for (Edge e : graph.get(cur)) {
                    if (e.type == pipe && !infected[e.to]) {
                        q.offer(e.to);
                        infected[e.to] = true;
                        count++;
                    }
                }
            }
        }
        
        ans = Math.max(ans, count);
    }
    
    static class Edge {
        int to, type;
        
        Edge(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }
}