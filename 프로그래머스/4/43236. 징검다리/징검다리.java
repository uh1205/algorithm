import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int ans = 0;

        Arrays.sort(rocks);
        
        int low = 1;
        int high = distance;
        
        while (low <= high) {
            int mid = (low + high) >>> 1;
            
            int prev = 0;
            int removed = 0;
            
            for (int rock : rocks) {
                if (rock - prev < mid) {
                    removed++;
                } else {
                    prev = rock;
                }
            }
            if (distance - prev < mid) {
                removed++;
            }
            
            if (removed > n) {
                high = mid - 1;
            } else {
                ans = Math.max(ans, mid);
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    // 바위를 n개 제거했을 때 거리의 최솟값 중 최댓값을 구하라
    // 거리의 최솟값을 K로 설정했을 때, 제거해야 하는 바위의 수가 n개 이하인가?
    
    // 설정한 최소 거리 mid보다 짧은 간격이 생기면, 해당 바위를 제거
    // 제거한 바위의 개수가 n보다 많다면, mid 값을 낮춰야 함
    // 제거한 바위의 개수가 n 이하라면, 더 큰 최솟값도 가능한지 확인하기 위해 mid 값을 높임
}