import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        List<Integer> list = new ArrayList<>();
        
        // 배열의 원소 범위가 0~9이므로, 나올 수 없는 값으로 초기값 설정
        int lastValue = -1;
        
        for (int num : arr) {
            // 현재 숫자가 바로 이전 숫자와 다를 때만 리스트에 추가
            if (num != lastValue) {
                list.add(num);
                lastValue = num; // 현재 숫자를 이전 숫자로 업데이트
            }
        }

        // List<Integer>를 int[]로 변환
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}