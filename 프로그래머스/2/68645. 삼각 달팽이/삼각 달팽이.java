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
        
        // int last = n * (1 + n) / 2; // 등차수열 합
        
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