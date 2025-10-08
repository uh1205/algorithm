import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int todayDays = dateToDays(today);
        
        // 약관 종류 → 유효기간(개월)
        Map<String, Integer> termMap = new HashMap<>();
        for (String t : terms) {
            StringTokenizer st = new StringTokenizer(t);
            termMap.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        
        List<Integer> ans = new ArrayList<>();
        
        for (int i = 0; i < privacies.length; i++) {
            StringTokenizer st = new StringTokenizer(privacies[i]);
            int collectionDays = dateToDays(st.nextToken());
            int duratioinDays = termMap.get(st.nextToken()) * 28;
            
            int expirationDays = collectionDays + duratioinDays;
            
            if (todayDays >= expirationDays) {
                ans.add(i + 1);
            }
        }
        
        return ans.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    // 날짜를 총 일수로 변환하는 함수
    int dateToDays(String date) {
        StringTokenizer st = new StringTokenizer(date, ".");
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        return (y * 12 * 28) + ((m - 1) * 28) + d;
    }
}