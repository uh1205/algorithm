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
        Queue<Node> q = new ArrayDeque<>();
        
        q.offer(new Node(begin, 0));
        
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.word.equals(target)) {
                return cur.count;
            }

            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canConvert(cur.word, words[i])) {
                    visited[i] = true;
                    q.offer(new Node(words[i], cur.count + 1));
                }
            }
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
    
    static class Node {
        String word;
        int count;
        
        Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}