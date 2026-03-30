import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
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
                for (int next : graph[cur]) {
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