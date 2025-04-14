import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int[] colors;
    static boolean isBipartite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());  // 테스트케이스 개수
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < k; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()); // 정점 개수
            int e = Integer.parseInt(st.nextToken()); // 간선 개수

            // 그래프 초기화
            graph = new ArrayList[v + 1];
            for (int i = 1; i <= v; i++) {
                graph[i] = new ArrayList<>();
            }

            // 간선 정보 입력
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph[u].add(w);
                graph[w].add(u);
            }

            // 색상 배열 (0: 미방문, 1: RED, -1: BLUE)
            colors = new int[v + 1];
            isBipartite = true;

            for (int i = 1; i <= v; i++) {
                if (colors[i] == 0) {
                    bfs(i);
                }
            }

            sb.append(isBipartite ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        colors[start] = 1;

        while (!q.isEmpty() && isBipartite) {
            int current = q.poll();

            for (int next : graph[current]) {
                if (colors[next] == 0) {
                    colors[next] = -colors[current];  // 서로 다른 색
                    q.offer(next);
                } else if (colors[next] == colors[current]) {
                    isBipartite = false;
                    return;
                }
            }
        }
    }
}