import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K + 1]; // dp[i] = i원을 만드는 경우의 수

        dp[0] = 1; // 아무 것도 고르지 않는 경우

        for (int coin : coins) {
            for (int i = coin; i <= K; i++) {
                dp[i] += dp[i - coin]; // [i-coin]원을 만드는 경우에서 [coin]원을 하나씩 추가
            }
        }

        System.out.println(dp[K]);
    }
}