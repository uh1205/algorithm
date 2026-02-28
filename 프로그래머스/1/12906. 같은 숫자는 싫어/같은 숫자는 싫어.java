import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        Deque<Integer> dq = new ArrayDeque<>();
        
        for (int num : arr) {
            if (dq.isEmpty() || dq.peek() != num) {
                dq.push(num);
            }
        }
        
        int n = dq.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = dq.pollLast();
        }
        
        return ans;
    }
}