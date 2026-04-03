import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int t = 0;
        int w = 0;
        int len = 0;
        int idx = 0;
        int cnt = 0;
        int n = truck_weights.length;
        
        Queue<Integer> q = new ArrayDeque<>();
        
        while (cnt < n) {
            t++;
            if (len == bridge_length) {
                int poll = q.poll();
                if (poll > 0) cnt++;
                w -= poll;
            } else {
                len++;
            }
            if (idx < n && w + truck_weights[idx] <= weight) {
                int truck = truck_weights[idx++];
                q.offer(truck);
                w += truck;
            } else {
                q.offer(0);
            }
        }
        
        return t;
    }
}