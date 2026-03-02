import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i < progresses.length; i++) {
            int remaining = 100 - progresses[i];
            int days = (remaining + speeds[i] - 1) / speeds[i];
            q.offer(days);
        }
        
        List<Integer> ans = new ArrayList<>();
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            int count = 1;
            
            while (!q.isEmpty() && q.peek() <= cur) {
                q.poll();
                count++;
            }
            
            ans.add(count);
        }
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}