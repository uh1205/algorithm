import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] memory;
    static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 앱 개수
        M = Integer.parseInt(st.nextToken()); // 확보해야 할 메모리

        memory = new int[N];
        cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        // 최대 비용: 앱당 비용은 최대 100, N개 → 100 * 100 = 10000
        int[] dp = new int[10001];

        for (int i = 0; i < N; i++) {
            int m = memory[i];
            int c = cost[i];

            // 뒤에서부터 갱신해야 중복 선택 방지 (0/1 Knapsack)
            for (int j = 10000; j >= c; j--) {
                dp[j] = Math.max(dp[j], dp[j - c] + m);
            }
        }

        // 조건을 만족하는 최소 비용 찾기
        for (int i = 0; i <= 10000; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                return;
            }
        }
    }
}