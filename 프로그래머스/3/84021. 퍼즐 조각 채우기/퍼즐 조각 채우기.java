import java.util.*;

class Solution {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] game_board, int[][] table) {
        List<List<int[]>> blanks = getPieces(game_board, 0);
        List<List<int[]>> puzzles = getPieces(table, 1);
        
        int ans = 0;
        boolean[] used = new boolean[puzzles.size()];
        
        for (List<int[]> blank : blanks) {
            for (int i = 0; i < puzzles.size(); i++) {
                if (used[i]) continue;
                
                List<int[]> puzzle = puzzles.get(i);
                if (puzzle.size() != blank.size()) continue;
                
                if (match(blank, puzzle)) {
                    ans += blank.size();
                    used[i] = true;
                    break;
                }
            }
        }
        
        return ans;
    }
    
    List<List<int[]>> getPieces(int[][] map, int target) {
        List<List<int[]>> pieces = new ArrayList<>();
        int n = map.length;
        boolean[][] visited = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == target && !visited[i][j]) {
                    pieces.add(bfs(i, j, map, visited, target));
                }
            }
        }
        
        return pieces;
    }
    
    List<int[]> bfs(int i, int j, int[][] map, boolean[][] visited, int target) {
        List<int[]> list = new ArrayList<>();
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{i, j});
        visited[i][j] = true;
        
        int n = map.length;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            list.add(cur);
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if (visited[nr][nc] || map[nr][nc] != target) continue;
                
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
        
        normalize(list);
        return list;
    }
    
    void normalize(List<int[]> list) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        for (int[] l : list) {
            minR = Math.min(minR, l[0]);
            minC = Math.min(minC, l[1]);
        }
        for (int[] l : list) {
            l[0] -= minR;
            l[1] -= minC;
        }
        list.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    }
    
    boolean match(List<int[]> blank, List<int[]> puzzle) {
        for (int r = 0; r < 4; r++) {
            if (isSame(blank, puzzle)) {
                return true;
            }
            rotate(puzzle);
        }
        return false;
    }
    
    boolean isSame(List<int[]> a, List<int[]> b) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }
        return true;
    }
    
    void rotate(List<int[]> a) {
        for (int[] v : a) {
            int r = v[0];
            int c = v[1];
            v[0] = c;
            v[1] = -r;
        }
        normalize(a);
    }
}