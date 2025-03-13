import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1; // 1을 빼고 하는 연산

            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
                // 1을 빼고 하는 연산과, 2를 나누고 하는 연산 중 적게 걸리는걸로
            }
            
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
                // 1을 빼고 하는 연산과, 3을 나누고 하는 연산 중 적게 걸리는걸로
            }
        }

        System.out.println(dp[N]);
    }
}