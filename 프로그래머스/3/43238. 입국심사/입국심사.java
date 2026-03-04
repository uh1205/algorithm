class Solution {
    public long solution(int n, int[] times) {
        int maxTime = 0;
        for (int t : times) {
            maxTime = Math.max(maxTime, t);
        }
        
        long left = 1;
        long right = (long) maxTime * n;
        long ans = right;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            if (isPossible(n, times, mid)) {
                ans = mid;
                right = mid - 1; // 더 짧은 시간 시도
            } else {
                left = mid + 1; // 더 긴 시간 시도
            }
        }
        
        return ans;
    }
    
    // t초 안에 n명 모두 심사 가능?
    boolean isPossible(int n, int[] times, long t) {
        long total = 0;
        for (int i = 0; i < times.length; i++) {
            total += t / times[i];
            if (total >= n) {
                return true;
            }
        }
        return false;
    }
}