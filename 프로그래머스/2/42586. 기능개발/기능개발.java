import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> ans = new ArrayList<>();
        
        int day = (100 - progresses[0] + speeds[0] - 1) / speeds[0];
        int count = 1;
        
        for (int i = 1; i < progresses.length; i++) {
            int cur = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
            
            if (cur <= day) {
                count++;
            } else {
                ans.add(count);
                day = cur;
                count = 1;
            }
        }
        ans.add(count);
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}