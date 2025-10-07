class Solution {
    public String solution(String new_id) {
        // 1. 소문자로 변환
        String lower = new_id.toLowerCase();

        // 2. 소문자, 숫자, -, _, .만 남기기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (isAllowed(ch)) sb.append(ch);
        }

        // 3. 연속된 .을 하나로 치환
        String str = sb.toString();
        sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '.' && sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
                continue;
            }
            sb.append(ch);
        }

        // 4. 처음이나 끝에 위치한 . 제거
        if (sb.length() > 0 && sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }

        // 5. 빈 문자열이면 a를 대입
        if (sb.length() == 0) sb.append('a');

        // 6. 16자 이상이면 15자로 자르고, 마지막이 .이라면 제거
        if (sb.length() > 15) {
            sb.setLength(15);
            if (sb.charAt(sb.length() - 1) == '.') {
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        // 7. 2자 이하라면 마지막 문자를 반복
        while (sb.length() <= 2) {
            sb.append(sb.charAt(sb.length() - 1));
        }

        return sb.toString();
    }

    boolean isAllowed(char ch) {
        return (ch >= 'a' && ch <= 'z') ||
                (ch >= '0' && ch <= '9') ||
                (ch == '-' || ch == '_' || ch == '.');
    }
}