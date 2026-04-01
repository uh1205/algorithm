import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        long low = 1;
        long high = distance;
        
        // 최소 거리를 mid로 설정했을 때, 제거해야 하는 바위의 개수가 n 이하?
        // 최댓값 구하기
        long ans = 0;
        
        while (low <= high) {
            long mid = (low + high) >>> 1;
            
            int removed = 0;
            int prev = 0;
            
            for (int rock : rocks) {
                if (rock - prev < mid) { // 최소 거리보다 짧으면 제거
                    removed++;
                } else {
                    prev = rock;
                }
            }
            if (distance - prev < mid) {
                removed++;
            }
            
            if (removed <= n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return (int) ans;
    }
}