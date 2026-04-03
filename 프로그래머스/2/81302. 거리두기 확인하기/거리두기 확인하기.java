import java.util.*;

class Solution {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    public int[] solution(String[][] places) {
        int[] ans = new int[5];
        for (int i = 0; i < 5; i++) {
            if (isGood(places[i])) {
                ans[i] = 1;
            }
        }
        return ans;
    }
    
    boolean isGood(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') {
                    if (!bfs(i, j, place)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    boolean bfs(int i, int j, String[] place) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});

        boolean[][] visited = new boolean[5][5];
        visited[i][j] = true;
        
        int dist = 0;
        
        while (dist++ < 2 && !q.isEmpty()) {
            int size = q.size();
            
            while (size-- > 0) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
                    if (visited[nr][nc]) continue;
                    if (place[nr].charAt(nc) == 'X') continue;

                    if (place[nr].charAt(nc) == 'P') return false;

                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        
        return true;
    }
}