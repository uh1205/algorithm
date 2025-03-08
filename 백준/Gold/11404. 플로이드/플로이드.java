import java.io.*;
import java.util.*;

class Main {

    static int n, m, a, b, c;
    static int[][] dist; // 최단 거리 배열
    static final int INF = 100_000_000; // 무한대를 나타내는 값 (100,000 * 100보다 큼)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 도시 개수
        m = Integer.parseInt(br.readLine()); // 버스 개수

        // 거리 배열 선언 & 초기화
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // 자기 자신으로 가는 비용은 0
        }

        // 간선 정보 입력
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); // 출발 도시
            b = Integer.parseInt(st.nextToken()); // 도착 도시
            c = Integer.parseInt(st.nextToken()); // 비용

            // 같은 경로에 대해 더 작은 비용으로 갱신
            dist[a][b] = Math.min(dist[a][b], c);
        }

        // 플로이드-워셜 알고리즘 수행
        for (int k = 1; k <= n; k++) { // 경유지
            for (int i = 1; i <= n; i++) { // 출발 도시
                for (int j = 1; j <= n; j++) { // 도착 도시
                    // k를 경유할 수 있다면
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF) sb.append("0 ");
                else sb.append(dist[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}