import java.util.*;

class Solution {
    List<List<Integer>> tree = new ArrayList<>();
    
    public int solution(int n, int[][] wires) {
        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            tree.get(a).add(b);
            tree.get(b).add(a);
        }
        int minDiff = n;
        for (int i = 0; i < n - 1; i++) {
            int count = dfs(wires[i], new boolean[n + 1], 0, 1);
            minDiff = Math.min(minDiff, Math.abs(n - 2 * count));
        }
        return minDiff;
    }
    
    int dfs(int[] del, boolean[] visited, int count, int cur) {
        for (int next : tree.get(cur)) {
            if (cur == del[0] && next == del[1]) continue;
            if (cur == del[1] && next == del[0]) continue;
            
            if (!visited[next]) {
                visited[next] = true;
                count = dfs(del, visited, count + 1, next);
            }
        }
        return count;
    }
}