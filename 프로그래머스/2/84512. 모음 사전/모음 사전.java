import java.util.*;

class Solution {
    String[] moeum = {"A", "E", "I", "O", "U"};
    List<String> words = new ArrayList<>();
    
    public int solution(String word) {
        dfs("");
        return words.indexOf(word);
    }
    
    void dfs(String cur) {
        words.add(cur);
        if (cur.length() == 5) return;
        for (int i = 0; i < 5; i++) {
            dfs(cur + moeum[i]);
        }
    }
}