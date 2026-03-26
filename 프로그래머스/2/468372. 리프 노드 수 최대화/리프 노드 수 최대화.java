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
                long W = 1;
                long cost = 0;

                // 2-블록
                for (int a = 0; a < i; a++) {
                    if (cost + W <= dist_limit) {
                        cost += W;
                        W *= 2;
                    } else {
                        long remain = dist_limit - cost;
                        W += remain; // (2-1) * remain
                        cost = dist_limit;
                        break;
                    }
                }

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