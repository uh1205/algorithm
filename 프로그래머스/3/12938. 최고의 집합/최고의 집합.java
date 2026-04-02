import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (n == 1) {
            return new int[]{s};
        }
        
        if (n > s) {
            return new int[]{-1};
        }
        
        int[] ans = new int[n];
        
        Arrays.fill(ans, s / n);
        
        for (int i = n - 1; i >= n - s % n; i--) {
            ans[i]++;
        }
        
        return ans;
    }
}
// 8
// 2 2 2 ... 2
// 2 3 3