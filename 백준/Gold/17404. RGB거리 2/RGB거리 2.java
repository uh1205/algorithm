import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = INF;

        // 첫 번째 집 색상 고정
        for (int first = 0; first < 3; first++) {
            int[][] dp = new int[N][3];

            // 초기화
            for (int c = 0; c < 3; c++) {
                if (c == first) dp[0][c] = cost[0][c];
                else dp[0][c] = INF;
            }

            // DP 수행
            for (int i = 1; i < N; i++) {
                dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            }

            // 마지막 집 색상은 첫 번째 집 색상과 달라야 함
            for (int last = 0; last < 3; last++) {
                if (last == first) continue;
                answer = Math.min(answer, dp[N - 1][last]);
            }
        }

        System.out.println(answer);
    }
}