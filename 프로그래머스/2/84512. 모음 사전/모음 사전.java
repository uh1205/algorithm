import java.util.*;

class Solution {
    String[] moeum = {"A", "E", "I", "O", "U"};
    List<String> words = new ArrayList<>();
    
    public int solution(String word) {
        for (int len = 1; len <= 5; len++) {
            dfs(new StringBuilder(), len, 0);
        }
        words.sort(null);
        return words.indexOf(word) + 1;
    }
    
    void dfs(StringBuilder sb, int len, int depth) {
        if (depth == len) {
            words.add(sb.toString());
            return;
        }
        for (int i = 0; i < 5; i++) {
            sb.append(moeum[i]);
            dfs(sb, len, depth + 1);
            sb.deleteCharAt(depth);
        }
    }
}