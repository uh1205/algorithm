import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[3][2];
        dp[0][0] = dp[0][1] = arr[0][0];
        dp[1][0] = dp[1][1] = arr[0][1];
        dp[2][0] = dp[2][1] = arr[0][2];

        for (int i = 1; i < N; i++) {
            int max0 = Math.max(dp[0][0], dp[1][0]);
            int max1 = Math.max(dp[0][0], Math.max(dp[1][0], dp[2][0]));
            int max2 = Math.max(dp[1][0], dp[2][0]);

            int min0 = Math.min(dp[0][1], dp[1][1]);
            int min1 = Math.min(dp[0][1], Math.min(dp[1][1], dp[2][1]));
            int min2 = Math.min(dp[1][1], dp[2][1]);

            dp[0][0] = arr[i][0] + max0;
            dp[1][0] = arr[i][1] + max1;
            dp[2][0] = arr[i][2] + max2;

            dp[0][1] = arr[i][0] + min0;
            dp[1][1] = arr[i][1] + min1;
            dp[2][1] = arr[i][2] + min2;
        }

        int max = Math.max(dp[0][0], Math.max(dp[1][0], dp[2][0]));
        int min = Math.min(dp[0][1], Math.min(dp[1][1], dp[2][1]));

        System.out.println(max + " " + min);
    }
}
