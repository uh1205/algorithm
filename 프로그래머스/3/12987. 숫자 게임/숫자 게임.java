import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int n = A.length;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        // A의 가장 큰 값부터 승부
        // 만약 B의 가장 큰 값이 A 값을 못 이기면,
        // B의 가장 작은 값이 상대해서 지도록 함
        int ans = 0;
        int idx = n - 1;
        
        for (int i = n - 1; i >= 0; i--) {
            if (A[i] < B[idx]) {
                ans++;
                idx--;
            }
        }
        
        return ans;
    }
}