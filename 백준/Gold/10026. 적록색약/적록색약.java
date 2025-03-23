import java.io.*;
import java.util.*;

class Main {

    static int N, cnt1, cnt2;
    static char[][] map;
    static boolean[][] visited1, visited2;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited1 = new boolean[N][N];
        visited2 = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited1[i][j]) {
                    bfs1(i, j);
                    cnt1++;
                }
                if (!visited2[i][j]) {
                    bfs2(i, j);
                    cnt2++;
                }
            }
        }

        System.out.println(cnt1 + " " + cnt2);
    }

    static void bfs1(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited1[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            char cch = map[cr][cc]; // 현재 문자

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                char nch = map[nr][nc]; // 다음 문자
                if (!visited1[nr][nc] && nch == cch) {
                    q.add(new int[]{nr, nc});
                    visited1[nr][nc] = true;
                }
            }
        }
    }

    static void bfs2(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited2[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            char cch = map[cr][cc]; // 현재 문자

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                
                char nch = map[nr][nc]; // 다음 문자
                if (visited2[nr][nc]) continue;
                if (nch == cch || (cch != 'B' && nch != 'B')) {
                    q.add(new int[]{nr, nc});
                    visited2[nr][nc] = true;
                }
            }
        }
    }

}