import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];     // 최소 연산 횟수
        int[] prev = new int[N + 1];   // 경로 복원용
        Arrays.fill(dp, -1);           // -1: 아직 방문하지 않음

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        dp[N] = 0; // 시작점은 연산 0회

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == 1) break; // 목표 도달 시 탐색 종료

            // 가능한 연산 결과들
            int[] nexts = {cur - 1, (cur % 2 == 0 ? cur / 2 : -1), (cur % 3 == 0 ? cur / 3 : -1)};
            for (int next : nexts) {
                if (next > 0 && dp[next] == -1) { // 범위 내 + 미방문
                    dp[next] = dp[cur] + 1;
                    prev[next] = cur;
                    q.offer(next);
                }
            }
        }

        // 결과 출력
        System.out.println(dp[1]);

        StringBuilder sb = new StringBuilder();
        List<Integer> path = new ArrayList<>();
        for (int cur = 1; cur != 0; cur = prev[cur]) {
            path.add(cur);
            if (cur == N) break; // 시작점까지 왔으면 종료
        }
        Collections.reverse(path);

        for (int num : path) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}