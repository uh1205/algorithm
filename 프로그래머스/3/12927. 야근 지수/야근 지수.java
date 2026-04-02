import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int w : works) {
            pq.offer(w);
        }
        
        while (!pq.isEmpty() && n-- > 0) {
            int task = pq.poll() - 1;
            if (task > 0) pq.offer(task);
        }
        
        long ans = 0;
        
        while (!pq.isEmpty()) {
            ans += Math.pow((long) pq.poll(), 2);
        }
        
        return ans;
    }
}