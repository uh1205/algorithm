import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        
        q.offer(1);
        visited[1] = true;
        
        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            count = size;
            
            while (size-- > 0) {
                int cur = q.poll();
                for (int next : graph.get(cur)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
        }
        
        return count;
    }
}