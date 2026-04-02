import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int ans = 0;
        int a = 0;
        int b = 0;
        
        while (b < B.length) {
            if (A[a] < B[b]) {
                ans++;
                a++;
            }
            b++;
        }
        
        
        return ans;
    }
}