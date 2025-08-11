import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map, dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        dp = new int[n][n]; // 해당 지점에서 시작했을 때 최대 이동 칸 수

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 방문하지 않은 곳에서 dfs 수행
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }

        // dfs 수행 결과 가장 많이 이동 가능한 칸의 수 찾기
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }

        System.out.println(result);
    }

    static void dfs(int r, int c) {
        dp[r][c] = 1; // 방문 처리 (현재 위치로 이동)

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
            if (map[nr][nc] <= map[r][c]) continue;

            // 방문하지 않은 경우 dfs 수행
            if (dp[nr][nc] == 0) {
                dfs(nr, nc);
            }
            
            dp[r][c] = Math.max(dp[r][c], dp[nr][nc] + 1);
        }
    }
}