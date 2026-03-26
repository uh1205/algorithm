class Solution {
    public int solution(int dist_limit, int split_limit) {
        long answer = 1;

        // i: 2-분배 횟수
        for (int i = 0; i <= 31; i++) {
            long pow2 = 1L << i;
            if (pow2 > split_limit) break;

            // 가능한 최대 j 계산
            int maxJ = 0;
            long temp = pow2;
            while (temp * 3 <= split_limit) {
                temp *= 3;
                maxJ++;
            }

            // j를 0 ~ maxJ까지 모두 시도 (부분 분배 위치 때문)
            for (int j = 0; j <= maxJ; j++) {
                long W = 1; // 현재 깊이의 노드 수
                long cost = 0; // 현재 깊이까지의 분배 노드 수

                // 2-블록
                for (int a = 0; a < i; a++) {
                    // 현재 깊이를 모두 분배 노드로 사용 가능한 경우
                    if (cost + W <= dist_limit) {
                        cost += W;
                        W *= 2; // 다음 깊이 노드 수 = (현재 깊이 노드 수)^2
                    } else {
                        // 분배 노드로 사용 가능한 남은 노드 수
                        long remain = dist_limit - cost;
                        // 모두 분배 노드로 사용 (기존 W에서 1 감소, 2 증가)
                        W += remain; // (2-1) * remain
                        cost = dist_limit;
                        break;
                    }
                }

                // 모든 분배 노드를 사용한 경우
                if (cost == dist_limit) {
                    answer = Math.max(answer, W);
                    continue;
                }

                // 3-블록
                for (int b = 0; b < j; b++) {
                    if (cost + W <= dist_limit) {
                        cost += W;
                        W *= 3;
                    } else {
                        long remain = dist_limit - cost;
                        W += 2 * remain; // (3-1) * remain
                        cost = dist_limit;
                        break;
                    }
                }

                answer = Math.max(answer, W);
            }
        }

        return (int) answer;
    }
}