import java.util.*;

class Solution {
    public int[] solution(int n) {
        if (n == 1) return new int[]{1};
        
        int[][] arr = new int[n][n];
        
        int[] dr = {1, 0, -1};
        int[] dc = {0, 1, -1};
        
        int r = 0, c = 0, d = 0, num = 1;
        
        while (true) {
            arr[r][c] = num++;
            
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if (nr < 0 || nc < 0 || nr >= n || nc >= n || arr[nr][nc] != 0) {
                d = (d + 1) % 3;
            }
            
            r += dr[d];
            c += dc[d];
            
            if (arr[r][c] != 0) break;
        }
        
        List<Integer> ans = new ArrayList<>();
        
        for (int[] row : arr) {
            for (int v : row) {
                if (v == 0) {
                    break;
                }
                ans.add(v);
            }
        }
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
}