import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int n = words.length;
        
        Queue<String> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        
        q.offer(begin);
        
        int count = 0;
        
        while (!q.isEmpty()) {
            count++;
            int size = q.size();
            while (size-- > 0) {
                String cur = q.poll();

                for (int i = 0; i < n; i++) {
                    if (!visited[i] && canTransform(cur, words[i])) {
                        if (words[i].equals(target)) {
                            return count;
                        }
                        visited[i] = true;
                        q.offer(words[i]);
                    }
                }
            }
        }
        
        return 0;
    }
    
    boolean canTransform(String from, String to) {
        int diff = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }
}