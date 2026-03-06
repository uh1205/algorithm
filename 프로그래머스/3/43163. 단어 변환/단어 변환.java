class Solution {
    static int ans = 100;
    
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && canSwitch(begin, words[i])) {
                dfs(i, words[i], target, words, visited, 1);
            }
        }
        
        return ans == 100 ? 0 : ans;
    }
    
    void dfs(int idx, String cur, String target, String[] words, boolean[] visited, int count) {
        if (cur.equals(target)) {
            ans = Math.min(ans, count);
        }
        visited[idx] = true;
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && canSwitch(cur, words[i])) {
                dfs(i, words[i], target, words, visited, count + 1);
                visited[i] = false;
            }
        }
    }
    
    boolean canSwitch(String from, String to) {
        int diff = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }
}