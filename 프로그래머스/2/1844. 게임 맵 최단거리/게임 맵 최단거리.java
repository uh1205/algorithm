import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        int[] dr = {0, 0, -1, 1};
        int[] dc = {-1, 1, 0, 0};
        
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        int dist = 0;
        boolean arrived = false;
        
        while (!q.isEmpty()) {
            dist++;
            int size = q.size();
            
            while (size-- > 0) {
                int[] cur = q.poll();
                int cr = cur[0];
                int cc = cur[1];

                if (cr == n - 1 && cc == m - 1) {
                    arrived = true;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if (nr < 0 || nc < 0 || nr >= n || nc >= m
                        || visited[nr][nc]
                        || maps[nr][nc] == 0) {
                        continue;
                    }

                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
            
            if (arrived) {
                break;
            }
        }
        
        return arrived ? dist : -1;
    }
}