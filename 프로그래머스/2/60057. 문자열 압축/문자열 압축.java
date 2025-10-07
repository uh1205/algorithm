public class Solution {
    public int solution(String s) {
        int len = s.length();
        int answer = len;

        // 압축 단위 1 ~ len/2까지 시도
        for (int unit = 1; unit <= len / 2; unit++) {
            StringBuilder sb = new StringBuilder();

            String prev = s.substring(0, unit); // 첫 덩어리
            int cnt = 1;

            // 단위 길이만큼 이동하면서 비교
            for (int i = unit; i <= len; i += unit) {
                // 현재 덩어리 구하기 (마지막 부분이 unit보다 짧을 수 있음)
                String cur;
                if (i + unit <= len) {
                    cur = s.substring(i, i + unit);
                } else {
                    cur = s.substring(i); // i부터 끝까지
                }

                // 이전 문자열과 같으면 카운트 증가
                if (cur.equals(prev)) {
                    cnt++;
                } else {
                    // 다르면 압축 결과에 추가
                    if (cnt > 1) sb.append(cnt);
                    sb.append(prev);
                    prev = cur; // 다음 비교 대상
                    cnt = 1;
                }
            }

            // 마지막 남은 부분 처리
            if (cnt > 1) sb.append(cnt);
            sb.append(prev);

            // 최솟값 갱신
            answer = Math.min(answer, sb.length());
        }

        return answer;
    }
}