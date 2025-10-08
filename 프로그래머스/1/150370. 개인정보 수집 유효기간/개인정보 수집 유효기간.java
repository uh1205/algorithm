import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 1. 약관 정보를 Map에 저장 (Key: 약관 종류, Value: 유효 기간(월))
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);
            termMap.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        // 2. 오늘 날짜를 총 일수로 변환
        int todayDays = dateToDays(today);

        // 파기할 개인정보 번호를 저장할 리스트
        List<Integer> expired = new ArrayList<>();

        // 3. 각 개인정보의 만료 여부를 확인
        for (int i = 0; i < privacies.length; i++) {
            StringTokenizer st = new StringTokenizer(privacies[i]);
            int collectionDays = dateToDays(st.nextToken()); // 개인정보 수집일
            int durationMonths = termMap.get(st.nextToken()); // 해당 약관의 유효 기간(월)

            // 만료일을 총 일수로 계산 (수집일 + 유효기간)
            // 유효기간은 해당 달수만큼 보관 가능하므로 만료일은 정확히 그 날짜가 됨
            int expirationDays = collectionDays + (durationMonths * 28);

            // 오늘 날짜가 만료일보다 크거나 같다면 파기 대상
            if (todayDays >= expirationDays) {
                expired.add(i + 1); // 개인정보 번호는 1부터 시작
            }
        }

        // 4. 리스트를 정수 배열로 변환하여 반환
        return expired.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    int dateToDays(String date) {
        StringTokenizer st = new StringTokenizer(date, ".");
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        return (y * 12 * 28) + (28 * (m - 1)) + d; // 1월은 28 곱하면 안 됨
    }
}