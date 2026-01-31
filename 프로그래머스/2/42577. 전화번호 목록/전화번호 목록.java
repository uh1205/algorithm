import java.util.HashMap;

class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> map = new HashMap<>();

        // 모든 전화번호를 해시맵에 저장
        for (String phone : phone_book) map.put(phone, 1);

        // 각 번호마다 자기 자신의 접두어가 맵에 있는지 확인
        for (String phone : phone_book) {
            for (int j = 1; j < phone.length(); j++) {
                if (map.containsKey(phone.substring(0, j))) {
                    return false;
                }
            }
        }
        return true;
    }
}