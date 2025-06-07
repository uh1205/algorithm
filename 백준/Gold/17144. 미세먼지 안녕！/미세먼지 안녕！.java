import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] map;
    static int purifierRow;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == -1) {
                    purifierRow = r; // 공기청정기 아래쪽 row
                }
            }
        }

        while (T-- > 0) {
            // 1. 미세먼지 확산
            spreadDust();

            // 2. 공기청정기 작동
            circulateAir();
        }

        int dust = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0) {
                    dust += map[r][c];
                }
            }
        }

        System.out.println(dust);
    }

    static void spreadDust() {
        int[][] copiedMap = copyMap();

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0) {
                    int spread = map[r][c] / 5;
                    int spreadCount = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                        if (map[nr][nc] == -1) continue;
                        copiedMap[nr][nc] += spread;
                        spreadCount++;
                    }
                    copiedMap[r][c] -= spreadCount * spread;
                }
            }
        }
        map = copiedMap;
    }

    static int[][] copyMap() {
        int[][] copied = new int[R][C];
        for (int i = 0; i < R; i++) {
            copied[i] = map[i].clone();
        }
        return copied;
    }

    static void circulateAir() {
        for (int r = purifierRow - 3; r >= 0; r--) {
            map[r + 1][0] = map[r][0]; // 반시계 방향
        }
        for (int r = purifierRow + 2; r < R; r++) {
            map[r - 1][0] = map[r][0]; // 시계 방향
        }

        for (int c = 1; c < C; c++) {
            map[0][c - 1] = map[0][c]; // 반시계 방향
            map[R - 1][c - 1] = map[R - 1][c]; // 시계 방향
        }

        for (int r = 1; r <= purifierRow - 1; r++) {
            map[r - 1][C - 1] = map[r][C - 1]; // 반시계 방향
        }
        for (int r = R - 2; r >= purifierRow; r--) {
            map[r + 1][C - 1] = map[r][C - 1]; // 시계 방향
        }

        for (int c = C - 2; c >= 1; c--) {
            map[purifierRow - 1][c + 1] = map[purifierRow - 1][c]; // 반시계 방향
            map[purifierRow][c + 1] = map[purifierRow][c]; // 시계 방향
        }

        map[purifierRow - 1][1] = 0;
        map[purifierRow][1] = 0;
    }
}