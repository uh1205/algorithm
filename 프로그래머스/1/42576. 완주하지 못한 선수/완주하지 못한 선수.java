import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> participantCounts = new HashMap<>();
        
        for (String name : participant) {
            participantCounts.put(name, participantCounts.getOrDefault(name, 0) + 1);
        }
        
        Map<String, Integer> completionCounts = new HashMap<>();
        
        for (String name : completion) {
            completionCounts.put(name, completionCounts.getOrDefault(name, 0) + 1);
        }
        
        String answer = "";
        
        for (Map.Entry<String, Integer> entry : participantCounts.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            
            if (!completionCounts.containsKey(name)) {
                answer = name;
                break;
            }
            
            if (completionCounts.get(name) < count) {
                answer = name;
                break;
            }
        }
        
        return answer;
    }
}