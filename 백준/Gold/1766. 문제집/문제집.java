import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<Integer>> graph;
    static int[] inDegree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        inDegree = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // B번 문제보다 A번 문제를 먼저 풀어야 함 (A → B)
            graph.get(A).add(B);
            inDegree[B]++;
        }

        topologicalSort();

        System.out.println(sb);
    }

    static void topologicalSort() {
        // 작은 번호부터 출력해야 하므로, 일반 큐 대신 우선순위 큐 사용
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                pq.add(i);
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(' ');

            for (int next : graph.get(cur)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    pq.add(next);
                }
            }
        }
    }
}