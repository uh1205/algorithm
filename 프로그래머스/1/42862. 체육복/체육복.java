import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        boolean[] hasSize = new boolean[n + 2];
        
        for (int r : reserve) {
            hasSize[r] = true;
        }
        
        List<Integer> realLost = new ArrayList<>();
        for (int l : lost) {
            if (hasSize[l]) {
                hasSize[l] = false;
            } else {
                realLost.add(l);
            }
        }
        
        realLost.sort(null);
        
        int noSize = 0;
        for (int l : realLost) {
            if (hasSize[l - 1]) {
                hasSize[l - 1] = false;
            } else if (hasSize[l + 1]) {
                hasSize[l + 1] = false;
            } else {
                noSize++;
            }
        }
        
        return n - noSize;
    }
}