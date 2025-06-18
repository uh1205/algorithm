import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 문제 수
        int M = Integer.parseInt(st.nextToken()); // 간선 수

        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[N + 1]; // 진입 차수

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);  // A → B
            inDegree[B]++;        // B의 진입 차수 증가
        }

        // 우선순위 큐 사용하여 쉬운 문제부터 처리
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 진입 차수가 0인 문제 먼저 큐에 넣기
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                pq.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(" ");

            for (int next : graph.get(now)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }

        System.out.println(sb.toString());
    }
}