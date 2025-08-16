import java.io.*;

public class Main {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // dp[len][d][mask]
        // 길이가 len인 계단 수 중, 마지막 숫자가 d, 지금까지 사용한 숫자 집합이 mask일 때 가능한 경우의 수
        int[][][] dp = new int[N + 1][10][1 << 10];
        final int ALL_MASKED = (1 << 10) - 1;

        for (int d = 1; d <= 9; d++) {
            dp[1][d][1 << d] = 1;
        }

        for (int len = 1; len < N; len++) {
            for (int d = 0; d <= 9; d++) {
                for (int mask = 0; mask <= ALL_MASKED; mask++) {
                    int cur = dp[len][d][mask];
                    if (cur == 0) continue; // 경우 없음

                    // 다음 숫자 선택
                    if (d > 0) {
                        int nd = d - 1;
                        int nextMask = mask | (1 << nd);
                        dp[len + 1][nd][nextMask] = (dp[len + 1][nd][nextMask] + cur) % MOD;
                    }
                    if (d < 9) {
                        int nd = d + 1;
                        int nextMask = mask | (1 << nd);
                        dp[len + 1][nd][nextMask] = (dp[len + 1][nd][nextMask] + cur) % MOD;
                    }
                }
            }
        }

        int result = 0;
        for (int d = 0; d <= 9; d++) {
            result = (result + dp[N][d][ALL_MASKED]) % MOD;
        }

        System.out.println(result);
    }
}