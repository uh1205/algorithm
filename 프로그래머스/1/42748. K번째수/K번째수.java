import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] ans = new int[commands.length];
        
        for (int c = 0; c < commands.length; c++) {
            int i = commands[c][0];
            int j = commands[c][1];
            int k = commands[c][2];
            
            int[] sub = Arrays.copyOfRange(array, i - 1, j);
            Arrays.sort(sub);
            ans[c] = sub[k - 1];
        }
        
        return ans;
    }
}