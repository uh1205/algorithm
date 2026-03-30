import java.util.*;

class Solution {
    Set<Integer> set = new HashSet<>();
    boolean[] visited;
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        dfs("", numbers);
        int ans = 0;
        for (int v : set) {
            if (isPrime(v)) ans++;
        }
        return ans;
    }
    
    void dfs(String cur, String numbers) {
        if (!cur.equals("")) {
            set.add(Integer.parseInt(cur));
        }
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(cur + numbers.charAt(i), numbers);
                visited[i] = false;
            }
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