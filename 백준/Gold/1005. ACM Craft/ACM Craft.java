import java.io.*;
import java.util.*;

public class Main {
    static int N, K, W;
    static int[] buildTime, result;
    static List<List<Integer>> graph;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            buildTime = new int[N + 1];
            result = new int[N + 1];
            indegree = new int[N + 1];
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
                result[i] = buildTime[i]; // 기본 건설 시간
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                indegree[to]++;
            }

            W = Integer.parseInt(br.readLine());

            topologySort();

            sb.append(result[W]).append('\n');
        }

        System.out.print(sb);
    }

    static void topologySort() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : graph.get(cur)) {
                result[next] = Math.max(result[next], result[cur] + buildTime[next]);
                indegree[next]--;

                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}