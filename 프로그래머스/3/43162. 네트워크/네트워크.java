
import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        if (n == 1) return 1;
        
        List<Integer>[] con = new List[n];
        for (int i = 0; i < n; i++) {
            con[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    con[i].add(j);
                }
            }
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                q.offer(i);
                ans++;
            }
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : con[cur]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
        }
        
        return ans;
    }
}