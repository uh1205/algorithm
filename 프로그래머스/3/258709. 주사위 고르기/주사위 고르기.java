import java.util.*;

class Solution {
    int n;
    int[][] dices;
    int maxWins = -1;
    int[] answer;

    public int[] solution(int[][] dice) {
        n = dice.length;
        dices = dice;
        answer = new int[n / 2];

        // A가 가져갈 주사위 조합을 생성
        combination(0, new ArrayList<>());

        return answer;
    }

    // 1. A의 주사위 조합 생성 (DFS)
    void combination(int start, List<Integer> selected) {
        if (selected.size() == n / 2) {
            // 조합이 완성되면 승리 횟수 계산
            calculateWins(selected);
            return;
        }

        for (int i = start; i < n; i++) {
            selected.add(i);
            combination(i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    void calculateWins(List<Integer> selectedA) {
        List<Integer> selectedB = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!selectedA.contains(i)) {
                selectedB.add(i);
            }
        }

        // 각 조합에 대한 모든 점수 합계 계산
        List<Integer> sumsA = new ArrayList<>();
        generateSums(0, 0, selectedA, sumsA);

        List<Integer> sumsB = new ArrayList<>();
        generateSums(0, 0, selectedB, sumsB);

        // 승리 횟수 계산
        Collections.sort(sumsB);
        int wins = 0;
        for (int sumA : sumsA) {
            // 이분 탐색으로 sumA보다 작은 sumB의 개수를 찾음
            int low = 0, high = sumsB.size();
            while (low < high) {
                int mid = (low + high) / 2;
                if (sumsB.get(mid) < sumA) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            wins += low;
        }

        // 최적의 조합 갱신
        if (wins > maxWins) {
            maxWins = wins;
            for (int i = 0; i < n / 2; i++) {
                answer[i] = selectedA.get(i) + 1;
            }
        }
    }

    // 모든 점수 합계를 생성하는 재귀 함수
    void generateSums(int idx, int currentSum, List<Integer> selected, List<Integer> sums) {
        if (idx == selected.size()) {
            sums.add(currentSum);
            return;
        }

        for (int face : dices[selected.get(idx)]) {
            generateSums(idx + 1, currentSum + face, selected, sums);
        }
    }
}