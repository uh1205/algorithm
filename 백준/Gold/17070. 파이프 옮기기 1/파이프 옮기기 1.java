import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1][3]; // [행][열][방향]

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작 위치: (1,2)에 가로 방향으로 존재
        dp[1][2][0] = 1;

        for (int r = 1; r <= N; r++) {
            for (int c = 3; c <= N; c++) { // (1,1)-(1,2)는 이미 초기화되어 있으므로 c는 3부터
                if (map[r][c] == 1) continue;

                // 가로
                dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][2];

                // 세로
                if (r > 1)
                    dp[r][c][1] = dp[r - 1][c][1] + dp[r - 1][c][2];

                // 대각선
                if (r > 1 && map[r - 1][c] == 0 && map[r][c - 1] == 0) {
                    dp[r][c][2] = dp[r - 1][c - 1][0] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][2];
                }
            }
        }

        int result = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
        System.out.println(result);
    }
}