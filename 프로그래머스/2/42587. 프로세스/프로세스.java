import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        List<Integer> max = new ArrayList<>();
        for (int p : priorities) {
            max.add(p);
        }
        max.sort(Comparator.reverseOrder());
        
        int i = 0;
        int count = 0;
        int ans = -1;
        
        while (true) {
            if (priorities[i] >= max.get(count)) {
                count++;
                priorities[i] = 0;
                if (i == location) {
                    ans = count;
                    break;
                }
            }
            i++;
            i %= priorities.length;
        }
        
        return ans;
    }
}