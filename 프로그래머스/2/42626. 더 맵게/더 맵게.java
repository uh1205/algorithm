import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.offer(s);
        }
        
        int count = 0;
        
        while (pq.size() >= 2 && pq.peek() < K) {
            pq.offer(pq.poll() + pq.poll() * 2);
            count++;
        }
        
        if (pq.peek() < K) return -1;
        return count;
    }
}