import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean hasTarget = false;
        for (String w : words) {
            if (w.equals(target)) {
                hasTarget = true;
                break;
            }
        }
        if (!hasTarget) return 0;
        
        boolean[] visited = new boolean[words.length];
        Queue<String> q = new ArrayDeque<>();
        q.offer(begin);
        
        int count = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            while (size-- > 0) {
                String cur = q.poll();
                
                if (cur.equals(target)) {
                    return count;
                }

                for (int i = 0; i < words.length; i++) {
                    if (!visited[i] && canConvert(cur, words[i])) {
                        visited[i] = true;
                        q.offer(words[i]);
                    }
                }
            }
            
            count++;
        }
        
        return 0;
    }
    
    boolean canConvert(String from, String to) {
        int diff = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                diff++;
            }
            if (diff > 1) return false;
        }
        return diff == 1;
    }
}