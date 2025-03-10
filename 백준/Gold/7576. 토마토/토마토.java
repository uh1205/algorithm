import java.io.*;
import java.util.*;

public class Main {
    static int N, M, unripeCount = 0;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static ArrayDeque<int[]> q = new ArrayDeque<>(); // ✅ LinkedList → ArrayDeque

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) q.add(new int[]{i, j});
                else if (map[i][j] == 0) unripeCount++; // ✅ 익지 않은 토마토 개수 저장
            }
        }

        // 모든 토마토가 이미 익은 상태이면 0 출력 후 종료
        if (unripeCount == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int days = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] current = q.poll();
                int cr = current[0], cc = current[1];

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d], nc = cc + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] != 0) continue;

                    map[nr][nc] = 1; // ✅ 방문 체크 & 익음 처리
                    unripeCount--;  // ✅ 익지 않은 토마토 개수 감소
                    q.add(new int[]{nr, nc});
                }
            }
            if (!q.isEmpty()) days++;
        }

        return (unripeCount == 0) ? days : -1; // ✅ 더 빠르게 검사 가능
    }
}