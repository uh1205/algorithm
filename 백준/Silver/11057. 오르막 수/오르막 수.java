import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 10_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // dp[i][j] = i자리 오르막 수 중에서 마지막 자리가 j인 수의 개수
        int[][] dp = new int[N + 1][10];

        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                }
            }
        }

        int result = 0;
        for (int i : dp[N]) {
            result = (result + i) % MOD;
        }

        System.out.println(result);
    }
}