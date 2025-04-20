import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map, dp;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // 아직 방문하지 않음
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int r, int c) {
        if (r == N - 1 && c == M - 1) return 1;

        if (dp[r][c] != -1) return dp[r][c]; // 이미 계산된 경로 수

        dp[r][c] = 0; // 초기화

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

            if (map[nr][nc] < map[r][c]) {
                dp[r][c] += dfs(nr, nc);
            }
        }

        return dp[r][c];
    }
}