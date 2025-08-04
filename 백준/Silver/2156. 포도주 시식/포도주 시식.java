import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n + 1];

        dp[1] = arr[1];
        if (n >= 2) dp[2] = arr[1] + arr[2];

        for (int i = 3; i <= n; i++) {
            int a = arr[i] + arr[i - 1] + dp[i - 3];
            int b = arr[i] + dp[i - 2];
            int c = dp[i - 1];
            dp[i] = Math.max(a, Math.max(b, c));
        }

        System.out.println(dp[n]);
    }
}