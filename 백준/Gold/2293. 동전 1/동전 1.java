import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 동전 종류 수
        int k = Integer.parseInt(st.nextToken()); // 목표 금액

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine()); // 각 동전의 가치
        }

        int[] dp = new int[k + 1]; // dp[i]: i원을 만드는 경우의 수
        dp[0] = 1; // 0원을 만드는 방법은 아무 동전도 사용하지 않는 경우 1개

        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] += dp[i - coin];
            }
        }

        System.out.println(dp[k]);
    }
}