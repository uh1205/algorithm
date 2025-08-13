import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;

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

        int[][][] dp = new int[N][3][3];

        for (int i = 0; i < 3; i++) {
            Arrays.fill(dp[0][i], INF);
            dp[0][i][i] = cost[0][i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                int a = Math.min(dp[i - 1][1][j], dp[i - 1][2][j]);
                dp[i][0][j] = a == INF ? INF : a + cost[i][0];

                int b = Math.min(dp[i - 1][0][j], dp[i - 1][2][j]);
                dp[i][1][j] = b == INF ? INF : b + cost[i][1];

                int c = Math.min(dp[i - 1][0][j], dp[i - 1][1][j]);
                dp[i][2][j] = c == INF ? INF : c + cost[i][2];
            }
        }

        int a = Math.min(dp[N - 1][0][1], dp[N - 1][0][2]);
        int b = Math.min(dp[N - 1][1][0], dp[N - 1][1][2]);
        int c = Math.min(dp[N - 1][2][0], dp[N - 1][2][1]);

        System.out.println(Math.min(a, Math.min(b, c)));
    }
}