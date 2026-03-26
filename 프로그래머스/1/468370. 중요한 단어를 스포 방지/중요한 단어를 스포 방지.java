import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        // 공개 단어, 비밀 단어 존재
        // 비밀 단어를 하나씩 공개함
        // 이미 공개되지 않은 단어면 ans++
        int n = message.length();
        
        Set<String> open = new HashSet<>();
        List<String> secret = new ArrayList<>();
        
        boolean[] closed = new boolean[n];
        for (int[] sr : spoiler_ranges) {
            for (int i = sr[0]; i <= sr[1]; i++) {
                closed[i] = true;
            }
        }
        
        int s = 0;
        boolean isSecret = false;
        for (int e = 0; e <= n; e++) {
            if (e == n || message.charAt(e) == ' ') {
                String word = message.substring(s, e);
                if (isSecret) secret.add(word);
                else open.add(word);
                s = e + 1;
                isSecret = false;
            } else if (closed[e]) {
                isSecret = true;
            }
        }
        
        int ans = 0;
        for (String w : secret) {
            if (!open.contains(w)) {
                ans++;
                open.add(w);
            }
        }
        
        return ans;
    }
}