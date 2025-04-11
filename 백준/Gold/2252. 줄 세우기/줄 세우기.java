import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] inDegree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegree = new int[N + 1];

        // 인접 리스트 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 입력 및 진입 차수 계산
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            inDegree[B]++;
        }

        topologicalSort();
        System.out.println(sb);
    }

    static void topologicalSort() {
        Queue<Integer> q = new ArrayDeque<>();

        // 진입 차수가 0인 노드부터 시작
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(' ');

            for (int next : graph.get(now)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}