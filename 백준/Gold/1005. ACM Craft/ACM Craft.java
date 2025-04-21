import java.io.*;
import java.util.*;

public class Main {
    static int N, W;
    static int[] inDegree, time, dp;
    static List<List<Integer>> graph, prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 건물 개수
            int K = Integer.parseInt(st.nextToken()); // 건설 순서 규칙 개수

            time = new int[N + 1]; // 건물 건설 시간
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            graph = new ArrayList<>();
            prev = new ArrayList<>();
            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
                prev.add(new ArrayList<>());
            }

            inDegree = new int[N + 1]; // 해당 건물 건설 전에 먼저 건설해야 할 건물 수

            // 건설 순서: Y 건설 전에 X 먼저 건설해야 함
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                graph.get(X).add(Y);
                inDegree[Y]++; // Y 건설하기 전에 건설해야 할 건물 수 증가
            }

            W = Integer.parseInt(br.readLine()); // 건설해야 할 건물

            topologicalSort();
            sb.append(dp[W]).append('\n');
        }

        System.out.println(sb);
    }

    static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        dp = new int[N + 1]; // 각 건물을 건설하기까지 걸리는 시간

        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll(); // 지금 건설할 건물 번호

            dp[cur] = time[cur]; // 일단 자신을 건설하는 시간으로 초기화

            // 자신 전에 건설해야 할 건물 중에 가장 오래 걸리는 시간을 더한 값으로 설정
            for (int p : prev.get(cur)) {
                dp[cur] = Math.max(dp[cur], dp[p] + time[cur]);
            }

            for (int next : graph.get(cur)) {
                prev.get(next).add(cur);
                inDegree[next]--;
                if (inDegree[next] == 0) q.add(next);
            }
        }
    }
}