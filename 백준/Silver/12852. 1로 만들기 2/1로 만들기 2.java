import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];      // 최소 연산 횟수 저장
        int[] prev = new int[N + 1];    // 이전 값 저장 (경로 추적용)

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0; // 1은 연산이 필요 없음

        for (int i = 2; i <= N; i++) {
            // 1을 뺀 경우
            if (dp[i] > dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
                prev[i] = i - 1;
            }
            // 2로 나누는 경우
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                prev[i] = i / 2;
            }
            // 3으로 나누는 경우
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                prev[i] = i / 3;
            }
        }

        // 최소 연산 횟수 출력
        System.out.println(dp[N]);

        // 경로 추적
        StringBuilder sb = new StringBuilder();
        int cur = N;
        while (cur != 0) {
            sb.append(cur).append(" ");
            if (cur == 1) break;
            cur = prev[cur];
        }
        System.out.println(sb);
    }
}