import java.util.*;

class Solution {
    String[] moeum = {"A", "E", "I", "O", "U"};
    List<String> words = new ArrayList<>();
    
    public int solution(String word) {
        dfs(new StringBuilder());
        return words.indexOf(word);
    }
    
    void dfs(StringBuilder sb) {
        words.add(sb.toString());
        if (sb.length() == 5) return;
        for (int i = 0; i < 5; i++) {
            dfs(sb.append(moeum[i]));
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}