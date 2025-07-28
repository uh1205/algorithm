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

            int[][] dp = new int[K + 2][K + 2];
            int[][] opt = new int[K + 2][K + 2]; // 최소가 되는 k를 저장

            for (int i = 1; i <= K; i++) {
                opt[i][i] = i;
            }

            for (int len = 1; len < K; len++) {
                for (int i = 1; i + len <= K; i++) {
                    int j = i + len;
                    dp[i][j] = Integer.MAX_VALUE;

                    // Knuth 최적화: k 범위를 줄임
                    for (int k = opt[i][j - 1]; k <= opt[i + 1][j]; k++) {
                        int cost = dp[i][k] + dp[k + 1][j] + prefixSum[j] - prefixSum[i - 1];
                        if (cost < dp[i][j]) {
                            dp[i][j] = cost;
                            opt[i][j] = k;
                        }
                    }
                }
            }

            sb.append(dp[1][K]).append('\n');
        }

        System.out.println(sb);
    }
}