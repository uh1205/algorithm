import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, year = 0;
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

        while (true) {
            year++;
            int[][] tempMap = copyMap(map);

            // 빙산 녹이기
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (map[i][j] > 0) {
                        int melted = map[i][j] - countSea(map, i, j);
                        tempMap[i][j] = Math.max(melted, 0);
                    }
                }
            }
            map = tempMap;

            // 이어진 빙산 개수 구하기
            int iceberg = countIceberg();
            if (iceberg >= 2) { // 분리된 경우
                System.out.println(year);
                return;
            } else if (iceberg == 0) { // 다 녹을 때까지 분리되지 않은 경우
                System.out.println(0);
                return;
            }
        }
    }

    static int countIceberg() {
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

    static int[][] copyMap(int[][] map) {
        int[][] copied = new int[N][M];
        for (int i = 0; i < N; i++) {
            copied[i] = map[i].clone();
        }
        return copied;
    }

    static int countSea(int[][] map, int r, int c) {
        int count = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (map[nr][nc] == 0) count++;
        }
        return count;
    }

}