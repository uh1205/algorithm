import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 연속 3잔 마실 수 없음
        // i번 포도주를 안 마신 경우, 마신 경우
        int[][] dp = new int[n][2];
        dp[0][1] = arr[0];

        if (n > 1) {
            dp[1][0] = arr[0];
            dp[1][1] = arr[0] + arr[1];
        }

        // ..x = ..ox, ..xx
        // ..o = ..xoo, ..xo
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = arr[i] + Math.max(
                    dp[i - 2][0] + arr[i - 1],
                    dp[i - 1][0]
            );
        }

        System.out.println(Math.max(dp[n - 1][0], dp[n - 1][1]));
    }
}
