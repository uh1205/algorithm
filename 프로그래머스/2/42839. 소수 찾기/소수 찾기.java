import java.util.*;

class Solution {
    Set<Integer> ans = new HashSet<>();
    boolean[] visited;
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        dfs(numbers, 0, "");
        return ans.size();
    }
    
    void dfs(String numbers, int depth, String val) {
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                String newVal = val + String.valueOf(numbers.charAt(i));
                int num = Integer.parseInt(newVal);
                if (!ans.contains(num) && isPrime(num)) {
                    ans.add(num);
                }
                dfs(numbers, depth + 1, newVal);
                visited[i] = false;
            }
        }
    }
    
    boolean isPrime(int x) {
        if (x < 2) return false;
        if (x == 2) return true;
        for (int i = 2; i < x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}