class Solution {
    public int solution(int dist_limit, int split_limit) {
        long dist = dist_limit;
        long split = split_limit;
        long answer = 1;

        long pow2 = 1;
        for (int twoCount = 0; pow2 <= split; twoCount++) {
            long remaining = split / pow2;
            long pow3 = 1;

            for (int threeCount = 0; pow3 <= remaining; threeCount++) {
                answer = Math.max(answer, evaluate(dist, twoCount, threeCount));

                if (pow3 > split / 3) {
                    break;
                }
                pow3 *= 3;
            }

            if (pow2 > split / 2) {
                break;
            }
            pow2 *= 2;
        }

        return (int) answer;
    }

    private long evaluate(long distLimit, int twoCount, int threeCount) {
        long remain = distLimit;
        long frontier = 1;

        for (int i = 0; i < twoCount; i++) {
            if (remain < frontier) {
                return frontier + remain;
            }
            remain -= frontier;
            frontier *= 2;
        }

        for (int i = 0; i < threeCount; i++) {
            if (remain < frontier) {
                return frontier + remain * 2;
            }
            remain -= frontier;
            frontier *= 3;
        }

        return frontier;
    }
}
