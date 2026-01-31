import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        // 1. 의상 종류별로 몇 개의 아이템이 있는지 카운트
        Map<String, Integer> map = new HashMap<>();
        
        for (String[] cloth : clothes) {
            String category = cloth[1];
            map.put(category, map.getOrDefault(category, 0) + 1);
        }

        // 2. 각 종류별 (개수 + 1)을 모두 곱함
        int answer = 1;
        for (int count : map.values()) {
            answer *= (count + 1);
        }

        // 3. 아무것도 입지 않은 경우 1가지를 빼고 반환
        return answer - 1;
    }
}