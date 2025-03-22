import java.util.*;

class Solution {
    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder();

        // 1단계: 대문자를 소문자로 변환
        new_id = new_id.toLowerCase();

        // 2~3단계: 유효한 문자만 추가 + 연속된 마침표 제거
        boolean dotFlag = false;
        for (char c : new_id.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-' || c == '_') {
                sb.append(c);
                dotFlag = false;
            } else if (c == '.') {
                if (!dotFlag) { // 연속된 마침표 방지
                    sb.append(c);
                    dotFlag = true;
                }
            }
        }

        // 4단계: 앞뒤 마침표 제거
        if (sb.length() > 0 && sb.charAt(0) == '.') sb.deleteCharAt(0);
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);

        // 5단계: 빈 문자열이면 "a" 대입
        if (sb.length() == 0) sb.append("a");

        // 6단계: 16자 이상이면 15자로 자르고 끝에 마침표 제거
        if (sb.length() > 15) {
            sb.setLength(15);
            if (sb.charAt(sb.length() - 1) == '.') sb.deleteCharAt(sb.length() - 1);
        }

        // 7단계: 길이가 2 이하라면 마지막 문자 반복 추가
        while (sb.length() < 3) {
            sb.append(sb.charAt(sb.length() - 1));
        }

        return sb.toString();
    }
}