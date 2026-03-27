import java.util.*;

class Solution {
    int N, F, K, ans = 0;
    List<List<Edge>> graph = new ArrayList<>();
    
    public int solution(int n, int infection, int[][] edges, int k) {
        N = n;
        F = infection;
        K = k;
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int type = edge[2];
            graph.get(x).add(new Edge(y, type));
            graph.get(y).add(new Edge(x, type));
        }
        
        dfs(new int[k], 0, 0);
        
        return ans;
    }
    
    void dfs(int[] pipes, int cur, int depth) {
        if (depth == K) {
            bfs(pipes);
            return;
        }
        
        if (cur != 1) {
            pipes[depth] = 1;
            dfs(pipes, 1, depth + 1);
        }
        if (cur != 2) {
            pipes[depth] = 2;
            dfs(pipes, 2, depth + 1);
        }
        if (cur != 3) {
            pipes[depth] = 3;
            dfs(pipes, 3, depth + 1);
        }
    }
    
    void bfs(int[] pipes) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] infected = new boolean[N + 1];
        
        infected[F] = true;
        
        int count = 1;
        
        for (int pipe : pipes) {
            for (int i = 1; i <= N; i++) {
                if (infected[i]) q.offer(i);
            }
            
            while (!q.isEmpty()) {
                int cur = q.poll();
                
                for (Edge e : graph.get(cur)) {
                    if (pipe == e.type && !infected[e.to]) {
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