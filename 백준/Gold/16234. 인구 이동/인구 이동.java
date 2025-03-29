import java.io.*;
import java.util.*;

class Main {
    static int N, L, R, day = 0;
    static int[][] map, unitedMap;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static boolean united = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        unitedMap = map;

        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }
            if (!united) break;
            visited = new boolean[N][N];
            map = unitedMap;
            united = false;
            day++;
        }

        System.out.println(day);
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;

        List<int[]> unities = new ArrayList<>();
        int unity = 0;
        int sum = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (visited[nr][nc]) continue;

                int diff = Math.abs(map[nr][nc] - map[cr][cc]);
                if (diff >= L && diff <= R) {
                    united = true;
                    if (unities.isEmpty()) {
                        unities.add(new int[]{cr, cc});
                        sum += map[cr][cc];
                        unity++;
                    }
                    unities.add(new int[]{nr, nc});
                    sum += map[nr][nc];
                    unity++;

                    q.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        if (!unities.isEmpty()) {
            int avg = sum / unity;
            for (int[] u : unities) {
                unitedMap[u[0]][u[1]] = avg;
            }
        }
    }
}