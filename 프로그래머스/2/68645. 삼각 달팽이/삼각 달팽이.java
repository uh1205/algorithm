import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[][] triangle = new int[n][n];
        int maxNum = n * (n + 1) / 2; // 채워야 할 총 숫자의 개수
        int[] answer = new int[maxNum];

        int r = -1, c = 0; // 시작 지점 보정 (첫 이동이 '하'이므로 r=0이 되도록 -1에서 시작)
        int num = 1;

        for (int i = 0; i < n; i++) { // i는 이동하는 '단계' (n번 방향을 바꿈)
            for (int j = i; j < n; j++) { // 각 단계마다 이동 거리가 n, n-1, n-2... 순으로 줄어듦
                if (i % 3 == 0) { // 하
                    r++;
                } else if (i % 3 == 1) { // 우
                    c++;
                } else { // 좌상 대각선
                    r--;
                    c--;
                }
                triangle[r][c] = num++;
            }
        }

        // 2차원 배열에서 채워진 숫자만 순서대로 1차원 배열에 담기
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = triangle[i][j];
            }
        }

        return answer;
    }
}