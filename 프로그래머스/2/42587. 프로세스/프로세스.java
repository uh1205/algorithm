import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> q = new ArrayDeque<>();
        
        for (int i = 0; i < priorities.length; i++) {
            q.offer(new int[]{priorities[i], i});
        }
        
        Arrays.sort(priorities);
        
        int maxIdx = priorities.length - 1;
        int ans = 0;
        
        while (true) {
            int[] cur = q.poll();
            
            if (cur[0] == priorities[maxIdx]) {
                ans++;
                maxIdx--;
                if (cur[1] == location) {
                    break;
                }
            } else {
                q.offer(cur);
            }
        }
        
        return ans;
    }
}