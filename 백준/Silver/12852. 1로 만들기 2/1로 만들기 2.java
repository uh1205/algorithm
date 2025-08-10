import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // dp[i][0] = 숫자 i를 만드는데 걸린 최소 연산 수
        // dp[i][1] = 이전 수 (어디서 왔는지 기록)
        int[][] dp = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        dp[N][0] = 0;

        for (int i = N; i > 1; i--) {
            int curOpCnt = dp[i][0];
            int nextPos;

            // 3으로 나누기
            if (i % 3 == 0) {
                nextPos = i / 3;
                if (curOpCnt + 1 < dp[nextPos][0]) {
                    dp[nextPos][0] = curOpCnt + 1;
                    dp[nextPos][1] = i;
                }
            }

            // 2로 나누기
            if (i % 2 == 0) {
                nextPos = i / 2;
                if (curOpCnt + 1 < dp[nextPos][0]) {
                    dp[nextPos][0] = curOpCnt + 1;
                    dp[nextPos][1] = i;
                }
            }

            // 1 빼기
            nextPos = i - 1;
            if (curOpCnt + 1 < dp[nextPos][0]) {
                dp[nextPos][0] = curOpCnt + 1;
                dp[nextPos][1] = i;
            }
        }

        int result = dp[1][0];
        System.out.println(result);

        // 역추적
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 1;

        for (int i = 0; i < result + 1; i++) {
            stack.push(num);
            num = dp[num][1]; // 이전 수
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result + 1; i++) {
            sb.append(stack.pop()).append(' ');
        }

        System.out.println(sb);
    }
}