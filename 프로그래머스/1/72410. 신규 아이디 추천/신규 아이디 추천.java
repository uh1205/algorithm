class Solution {
    public String solution(String new_id) {
        // 대문자 -> 소문자
        StringBuilder sb = new StringBuilder(new_id.toLowerCase());

        // 사용 불가 문자 제거
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 'a' && c <= 'z') continue;
            if (c >= '0' && c <= '9') continue;
            if (c == '-' || c == '_' || c == '.') continue;
            sb.deleteCharAt(i);
            i--;
        }

        // 연속 점 -> 하나의 점
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '.' && i != sb.length() - 1) {
                while (sb.charAt(i + 1) == '.') {
                    sb.deleteCharAt(i + 1);
                    if (i + 1 == sb.length()) break;
                }
            }
        }

        // 맨 앞 점 제거
        if (sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }

        // 맨 뒤 점 제거
        if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }

        // 빈 문자열이면 a 대입
        if (sb.length() == 0) {
            sb.append('a');
        }

        // 길이가 16 이상이면 뒤에거 날리기
        if (sb.length() >= 16) {
            sb = new StringBuilder(sb.substring(0, 15)); // 0~15}
        }

        // 마지막이 .이면 제거
        int last = sb.length() - 1;
        if (sb.charAt(last) == '.') sb.deleteCharAt(last);

        // 길이가 2이하면 마지막 문자 늘리기
        char lastC = sb.charAt(sb.length() - 1);
        if (sb.length() == 1) sb.append(lastC).append(lastC);
        else if (sb.length() == 2) sb.append(lastC);
        
        return sb.toString();
    }
}