import java.util.*;

class Solution {
    Set<Integer> numberSet = new HashSet<>();

    public int solution(String numbers) {
        // 1. 모든 숫자 조합 생성 (재귀 순열)
        recursive("", numbers);

        int count = 0;
        // 2. 생성된 숫자 중 소수 개수 파악
        for (int num : numberSet) {
            if (isPrime(num)) count++;
        }

        return count;
    }

    // 모든 가능한 숫자를 조합하는 재귀 함수
    private void recursive(String current, String others) {
        // 현재까지 만들어진 숫자가 있다면 Set에 추가
        if (!current.equals("")) {
            numberSet.add(Integer.parseInt(current));
        }

        // 남은 숫자들로 새로운 조합 만들기
        for (int i = 0; i < others.length(); i++) {
            recursive(current + others.charAt(i), 
                      others.substring(0, i) + others.substring(i + 1));
        }
    }

    // 소수 판별 함수
    private boolean isPrime(int n) {
        if (n < 2) return false; // 0과 1은 소수가 아님
        
        // 에라토스테네스의 체 원리: 제곱근까지만 확인
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}