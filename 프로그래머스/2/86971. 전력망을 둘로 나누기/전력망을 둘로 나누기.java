import java.util.*;

class Solution {
    List<List<Integer>> tree = new ArrayList<>();
    int n;
    int minDiff;
    
    public int solution(int n, int[][] wires) {
        this.n = n;
        minDiff = n;
        
        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }
        
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        
        dfs(1, 0);
        
        return minDiff;
    }
    
    int dfs(int cur, int parent) {
        int count = 1;
        for (int next : tree.get(cur)) {
            if (next != parent) {
                int childs = dfs(next, cur);
                count += childs;
                
                int diff = Math.abs(childs - (n - childs));
                minDiff = Math.min(minDiff, diff);
            }
        }
        return count;
    }
}