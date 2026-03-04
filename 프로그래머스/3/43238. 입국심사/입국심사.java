class Solution {
    public long solution(int n, int[] times) {
        long maxTime = 0;
        for (int time : times) {
            if (time > maxTime) maxTime = time;
        }
        
        long left = 1;
        long right = maxTime * n;
        long ans = right;
        
        while (left <= right) {
            long mid = (left + right) >>> 1;
            
            long total = 0;
            for (int time : times) {
                total += mid / time;
                if (total >= n) break;
            }
            
            if (total >= n) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return ans;
    }
}