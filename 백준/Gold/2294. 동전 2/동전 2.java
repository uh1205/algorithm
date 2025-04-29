import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 10_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Set<Integer> coins = new HashSet<>();

        for (int i = 0; i < N; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        int[] dp = new int[K + 1]; // dp[i] = i원을 만드는데 필요한 동전의 최소 개수

        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= K; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        System.out.println(dp[K] == INF ? -1 : dp[K]);
    }
}