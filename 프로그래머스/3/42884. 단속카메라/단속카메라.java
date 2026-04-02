import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        int ans = 0;
        int last = Integer.MIN_VALUE;
        
        for (int[] r : routes) {
            if (last < r[0]) {
                ans++;
                last = r[1];
            }
        }
        
        return ans;
    }
}