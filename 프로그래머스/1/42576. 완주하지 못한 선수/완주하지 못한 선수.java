import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        // 1. HashMap 생성 (이름, 인원수)
        Map<String, Integer> hm = new HashMap<>();
        
        // 2. 참가자 명단을 해시맵에 기록
        for (String player : participant) {
            hm.put(player, hm.getOrDefault(player, 0) + 1);
        }
        
        // 3. 완주자 명단을 보며 인원수 차감
        for (String player : completion) {
            hm.put(player, hm.get(player) - 1);
        }
        
        // 4. 남아있는 인원수가 0이 아닌 선수를 탐색
        for (String key : hm.keySet()) {
            if (hm.get(key) != 0) {
                answer = key;
                break;
            }
        }
        
        return answer;
    }
}