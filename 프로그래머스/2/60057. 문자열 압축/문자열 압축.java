class Solution {
    public int solution(String s) {
        int minLength = s.length(); // 압축 전의 길이를 초기 최소 길이로 설정

        // 압축 단위를 1부터 s.length() / 2까지 변경하며 최소 길이를 찾는다.
        for (int unit = 1; unit <= s.length() / 2; unit++) {
            StringBuilder compressed = new StringBuilder(); // 압축된 문자열을 저장할 StringBuilder
            String prev = s.substring(0, unit); // 첫 번째 단위 문자열을 초기값으로 설정
            int count = 1; // 같은 단위 문자열이 반복된 횟수

            // unit 크기씩 문자열을 순차적으로 탐색
            for (int i = unit; i < s.length(); i += unit) {
                int end = Math.min(i + unit, s.length()); // 범위를 넘어가지 않도록 end 값 설정
                String next = s.substring(i, end); // 다음 단위 문자열 추출
                
                if (next.equals(prev)) { // 이전 문자열과 같다면 카운트 증가
                    count++;
                } else { // 이전 문자열과 다르면 압축 문자열을 추가하고 초기화
                    if (count > 1) compressed.append(count); // 반복 횟수가 1보다 크면 숫자 추가
                    compressed.append(prev); // 압축된 문자열 추가
                    prev = next; // 이전 문자열을 현재 문자열로 갱신
                    count = 1; // 카운트 초기화
                }
            }

            // 마지막으로 남아있는 문자열 처리
            if (count > 1) compressed.append(count); // 반복 횟수가 1보다 크면 숫자 추가
            compressed.append(prev); // 마지막 문자열 추가

            // 현재 압축된 문자열 길이와 최소 길이를 비교하여 갱신
            minLength = Math.min(minLength, compressed.length());
        }

        return minLength; // 가장 짧은 압축된 문자열 길이를 반환
    }
}