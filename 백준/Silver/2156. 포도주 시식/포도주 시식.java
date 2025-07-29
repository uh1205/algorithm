import java.io.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solve(arr));
    }

    static int solve(int[] arr) {
        if (n == 1) return arr[1];
        if (n == 2) return arr[1] + arr[2];

        int[] dp = new int[n + 1];

        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];

        for (int i = 3; i <= n; i++) {
            int a = dp[i - 1];
            int b = arr[i] + dp[i - 2];
            int c = arr[i] + arr[i - 1] + dp[i - 3];
            dp[i] = Math.max(a, Math.max(b, c));
        }

        return dp[n];
    }
}