import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());

            int[] arr = new int[K + 1];
            int[] prefixSum = new int[K + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                prefixSum[i] = prefixSum[i - 1] + arr[i];
            }

            int[][] dp = new int[K + 1][K + 1];

            for (int len = 1; len < K; len++) {
                for (int i = 1; i <= K - len; i++) {
                    int j = i + len;
                    dp[i][j] = Integer.MAX_VALUE;

                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]
                                + prefixSum[j] - prefixSum[i - 1]);
                    }
                }
            }

            sb.append(dp[1][K]).append('\n');
        }

        System.out.println(sb);
    }
}