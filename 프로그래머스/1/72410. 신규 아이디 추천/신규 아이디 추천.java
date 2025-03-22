class Solution {
    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder();

        // 1~3단계: 유효한 문자만 추가하면서 연속된 '.' 제거
        boolean dotFlag = false;
        for (char c : new_id.toCharArray()) {
            if (c >= 'A' && c <= 'Z') { // 1단계: 대문자 -> 소문자
                c += 32;
            }

            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-' || c == '_') {
                sb.append(c);
                dotFlag = false;
            } else if (c == '.') {
                if (!dotFlag && sb.length() > 0) { // 3단계: 연속된 '.' 방지 + 앞쪽 '.' 제거
                    sb.append(c);
                    dotFlag = true;
                }
            }
        }

        // 4단계: 마지막 문자가 '.'이면 제거
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
            sb.setLength(sb.length() - 1);
        }

        // 5단계: 빈 문자열이면 "a" 추가
        if (sb.length() == 0) sb.append("a");

        // 6단계: 15자 초과 시 잘라내고, 마지막 문자가 '.'이면 제거
        if (sb.length() > 15) {
            sb.setLength(15);
            if (sb.charAt(14) == '.') sb.setLength(14);
        }

        // 7단계: 3자 미만이면 마지막 문자 반복
        while (sb.length() < 3) {
            sb.append(sb.charAt(sb.length() - 1));
        }

        return sb.toString();
    }
}