import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] W;
    static int[][] dp;
    static final int INF = 1_000_000_000; // 1e9: 충분히 큰 값
    static final int START = 0;

    static int tsp(int visited, int cur) {
        // 모든 도시 방문 완료 → 시작점으로 복귀
        if (visited == (1 << N) - 1) {
            return (W[cur][START] == 0) ? INF : W[cur][START];
        }
        if (dp[visited][cur] != -1) return dp[visited][cur];

        int best = INF;
        // 다음 도시 선택
        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0) continue;    // 이미 방문
            if (W[cur][next] == 0) continue;               // 길 없음
            int cost = tsp(visited | (1 << next), next);
            if (cost == INF) continue;                     // 이후 불가능 경로
            best = Math.min(best, cost + W[cur][next]);
        }
        return dp[visited][cur] = best;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        N = n;
        W = new int[N][N];
        dp = new int[1 << N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int[] row : dp) Arrays.fill(row, -1);

        int ans = tsp(1 << START, START);

        // 요구사항에 맞춰 StringBuilder 사용
        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.println(sb.toString());
    }
}