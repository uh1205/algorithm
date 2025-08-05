import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[][] map = new int[2][n + 1];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][n + 1];

            dp[0][1] = map[0][1];
            dp[1][1] = map[1][1];

            for (int i = 2; i <= n; i++) {
                dp[0][i] = map[0][i] + Math.max(dp[1][i - 1], dp[1][i - 2]);
                dp[1][i] = map[1][i] + Math.max(dp[0][i - 1], dp[0][i - 2]);
            }

            sb.append(Math.max(dp[0][n], dp[1][n])).append('\n');
        }

        System.out.println(sb);
    }
}