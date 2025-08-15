import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 10 * 1_000_000 + 1;
    static int N;
    static int[][] W;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        W = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][1 << N];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        System.out.println(tsp(0, 1));
    }

    static int tsp(int cur, int visited) {
        if (visited == (1 << N) - 1) {
            return W[cur][0] == 0 ? INF : W[cur][0];
        }

        if (dp[cur][visited] != -1) {
            return dp[cur][visited];
        }

        int min = INF;

        for (int next = 0; next < N; next++) {
            if (W[cur][next] == 0 || (visited & (1 << next)) != 0) continue;
            int cost = W[cur][next] + tsp(next, visited | (1 << next));
            min = Math.min(min, cost);
        }

        return dp[cur][visited] = min;
    }
}