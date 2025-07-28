import java.io.*;
import java.util.*;

public class Main {
    static int[] files;
    static int[][] dp, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            files = new int[K + 1]; // 1-based 인덱스 사용
            dp = new int[K + 1][K + 1];
            sum = new int[K + 1][K + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                sum[i][i] = files[i];
            }

            // 누적합 배열 미리 계산
            for (int i = 1; i <= K; i++) {
                for (int j = i + 1; j <= K; j++) {
                    sum[i][j] = sum[i][j - 1] + files[j];
                }
            }

            // DP 수행
            for (int len = 1; len < K; len++) {
                for (int i = 1; i + len <= K; i++) {
                    int j = i + len;
                    dp[i][j] = Integer.MAX_VALUE;

                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[i][j]);
                    }
                }
            }

            sb.append(dp[1][K]).append('\n');
        }

        System.out.print(sb);
    }
}