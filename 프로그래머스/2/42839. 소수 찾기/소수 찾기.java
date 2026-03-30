import java.util.*;

class Solution {
    Set<Integer> set = new HashSet<>();
    boolean[] visited;
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        dfs("", numbers);
        return set.size();
    }
    
    void dfs(String cur, String numbers) {
        for (int i = 0; i < numbers.length(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            
            String next = cur + numbers.charAt(i);
            int val = Integer.parseInt(next);
            if (!set.contains(val) && isPrime(val)) {
                set.add(val);
            }
            
            dfs(next, numbers);
            visited[i] = false;
        }
    }
    
    boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}