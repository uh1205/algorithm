import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i < bridge_length; i++) {
            q.offer(0);
        }

        int t = 0;
        int w = 0;
        int idx = 0;
        
        while (idx < truck_weights.length) {
            t++;
            w -= q.poll();
            
            if (w + truck_weights[idx] <= weight) {
                int truck = truck_weights[idx++];
                q.offer(truck);
                w += truck;
            } else {
                q.offer(0);
            }
        }
        
        return t + bridge_length;
    }
}