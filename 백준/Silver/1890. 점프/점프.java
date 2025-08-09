import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 해당 지점까지 갈 수 있는 경우의 수
        long[][] dp = new long[N][N];

        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int jump = map[i][j];
                if (jump == 0 || dp[i][j] == 0) continue;
                
                if (i + jump < N) dp[i + jump][j] += dp[i][j];
                if (j + jump < N) dp[i][j + jump] += dp[i][j];
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}