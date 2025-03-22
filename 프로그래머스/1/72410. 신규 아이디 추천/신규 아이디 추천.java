class Solution {
    public String solution(String new_id) {
        // 1단계: 대문자를 소문자로 변환
        new_id = new_id.toLowerCase();

        // 2단계: 허용된 문자만 남기기 (소문자, 숫자, '-', '_', '.')
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");

        // 3단계: 연속된 '.'을 하나로 변경
        new_id = new_id.replaceAll("[.]{2,}", ".");

        // 4단계: 처음과 끝의 '.' 제거
        new_id = new_id.replaceAll("^[.]|[.]$", "");

        // 5단계: 빈 문자열이면 "a"로 변경
        if (new_id.isEmpty()) new_id = "a";

        // 6단계: 길이를 15자로 제한 후 끝에 '.' 제거
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("[.]$", "");
        }

        // 7단계: 길이가 2 이하라면 마지막 문자를 반복하여 길이 3으로 확장
        while (new_id.length() < 3) {
            new_id += new_id.charAt(new_id.length() - 1);
        }

        return new_id;
    }
}