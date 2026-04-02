import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> {
            return a[0] - b[0];
        });
        
        int ans = 1;
        int left = Integer.MIN_VALUE;
        int right = Integer.MAX_VALUE;
        
        for (int[] r : routes) {
            if (right >= r[0]) { // 겹침
                left = r[0];
                right = Math.min(right, r[1]);
            } else {
                ans++;
                left = r[0];
                right = r[1];
            }
        }
        
        return ans;
    }
}
// .     .
//   . .

// .  .
//   .  .

