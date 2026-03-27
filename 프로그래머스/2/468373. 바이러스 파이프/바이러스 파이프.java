import java.util.*;

class Solution {
    int n, k, ans = 0;
    List<List<Edge>> graph = new ArrayList<>();
    
    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.k = k;
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] e : edges) {
            int x = e[0], y = e[1], t = e[2];
            graph.get(x).add(new Edge(y, t));
            graph.get(y).add(new Edge(x, t));
        }
        
        Set<Integer> infected = new HashSet<>();
        infected.add(infection);
        
        dfs(0, 0, infected);
        
        return ans;
    }
    
    void dfs(int depth, int cur, Set<Integer> infected) {
        ans = Math.max(ans, infected.size());
        
        if (depth == k) {
            return;
        }
        
        for (int i = 1; i <= 3; i++) {
            if (cur != i) {
                dfs(depth + 1, i, bfs(i, infected));
            }
        }
    }
    
    Set<Integer> bfs(int pipe, Set<Integer> infected) {
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 1; i <= n; i++) {
            if (infected.contains(i)) q.offer(i);
        }
        
        Set<Integer> newInfected = new HashSet(infected);
        
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Edge e : graph.get(cur)) {
                if (e.type == pipe && !newInfected.contains(e.to)) {
                    q.offer(e.to);
                    newInfected.add(e.to);
                }
            }
        }
        
        return newInfected;
    }
    
    static class Edge {
        int to, type;
        
        Edge(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }
}