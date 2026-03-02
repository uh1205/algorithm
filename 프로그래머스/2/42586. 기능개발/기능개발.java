import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        
        int[] left = new int[n];
        
        for (int i = 0; i < n; i++) {
            left[i] = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
        }
        
        List<Integer> ans = new ArrayList<>();
        
        int day = left[0];
        int count = 0;
        int i = 0;
        
        while (i < n) {
            if (day >= left[i]) {
                count++;
                i++;
            } else {
                ans.add(count);
                count = 0;
                while (day < left[i]) {
                    day++;
                }
            }
        }
        ans.add(count);
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}