import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {
            int count = countIcebergs(); // 이어진 빙산 개수 구하기

            if (count == 0) { // 다 녹을 때까지 분리되지 않은 경우
                System.out.println(0);
                break;
            }

            if (count >= 2) { // 분리된 경우
                System.out.println(year);
                break;
            }

            meltIcebergs(); // 빙산 녹이기
            year++;
        }
    }

    static int countIcebergs() {
        int count = 0;
        visited = new boolean[N][M];

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    count++;
                    bfs(i, j);
                }
            }
        }
        return count;
    }

    static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (!visited[nr][nc] && map[nr][nc] > 0) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }

    static void meltIcebergs() {
        int[][] melt = new int[N][M];

        // 바닷물에 닿은 면 수 계산
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (map[i][j] > 0) {
                    int sea = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (map[nr][nc] == 0) sea++;
                    }
                    melt[i][j] = sea;
                }
            }
        }
        
        // 높이 감소 적용
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                map[i][j] = Math.max(0, map[i][j] - melt[i][j]);
            }
        }
    }
    
}