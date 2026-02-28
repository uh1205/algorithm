import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> ans = new ArrayList<>();
        
        int prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (prev != arr[i]) {
                ans.add(prev);
                prev = arr[i];
            }
        }
        ans.add(prev);
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
}