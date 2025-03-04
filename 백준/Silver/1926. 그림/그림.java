import java.io.*;
import java.util.*;

class Main {

    static int N, M, cnt = 0, max = 0;

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
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    cnt++;
                    visited[i][j] = true;
                    max = Math.max(max, bfs(i, j));
                }
            }
        }
        System.out.println(cnt + "\n" + max);
    }

    static int bfs(int r, int c) {
        int size = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c}); // 처음 위치

        while (!q.isEmpty()) {
            int[] cur = q.poll(); // 현재 위치
            int cr = cur[0];
            int cc = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i]; // 다음 위치
                int nc = cc + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (map[nr][nc] == 1 && !visited[nr][nc]) {
                    size++;
                    q.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        return size;
    }

}
