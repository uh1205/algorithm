class Solution {
    public int solution(String s) {
        int minLength = s.length(); // 초기 최솟값 설정 (압축을 안 했을 경우)

        // 압축 단위를 1부터 절반까지 확인
        for (int unit = 1; unit <= s.length() / 2; unit++) {
            StringBuilder compressed = new StringBuilder();
            String prev = s.substring(0, unit); // 첫 단위
            int count = 1; // 연속된 개수

            // unit 크기씩 이동하면서 압축
            for (int i = unit; i < s.length(); i += unit) {
                // 현재 단위와 다음 단위를 비교 (범위 체크 필수)
                String next = (i + unit <= s.length()) ? s.substring(i, i + unit) : s.substring(i);

                if (prev.equals(next)) { // 같은 문자열이면 개수 증가
                    count++;
                } else { // 다르면 압축 결과 추가
                    if (count > 1) compressed.append(count); // 반복된 경우만 숫자 추가
                    compressed.append(prev);
                    prev = next; // 새로운 단어로 변경
                    count = 1;
                }
            }

            // **마지막 남은 문자열 추가 (중요)**
            if (count > 1) compressed.append(count);
            compressed.append(prev);

            minLength = Math.min(minLength, compressed.length()); // 최소 길이 갱신
        }

        return minLength;
    }
}