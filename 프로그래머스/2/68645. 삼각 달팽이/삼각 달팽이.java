import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[][] arr = new int[n][n];
            
        int r = -1, c = 0;
        int num = 1;
        
        for (int i = 0; i < n; i++) { // 단계
            for (int j = 0; j < n - i; j++) { // 이동 거리
                if (i % 3 == 0) {
                    r++;
                } else if (i % 3 == 1) {
                    c++;
                } else {
                    r--;
                    c--;
                }
                arr[r][c] = num++;
            }
        }
        
        int[] ans = new int[num - 1];
        
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                ans[idx++] = arr[i][j];
            }
        }
        
        return ans;
    }
}