import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M;
    static Shark[][] map;
    static int[] dr = {0, -1, 1, 0, 0}; // 1:위, 2:아래, 3:오른쪽, 4:왼쪽
    static int[] dc = {0, 0, 0, 1, -1};

    static class Shark {
        int r, c, s, d, z;
        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            // 속도 최적화
            if (d == 1 || d == 2) s %= (R - 1) * 2;
            else s %= (C - 1) * 2;

            map[r][c] = new Shark(r, c, s, d, z);
        }

        int answer = 0;

        // 낚시왕 이동 (1열부터 C열까지)
        for (int king = 1; king <= C; king++) {
            // 1. 현재 열에서 가장 가까운 상어 잡기
            for (int r = 1; r <= R; r++) {
                if (map[r][king] != null) {
                    answer += map[r][king].z;
                    map[r][king] = null;
                    break;
                }
            }

            // 2. 상어 이동
            Shark[][] newMap = new Shark[R + 1][C + 1];
            for (int r = 1; r <= R; r++) {
                for (int c = 1; c <= C; c++) {
                    if (map[r][c] != null) {
                        Shark sh = map[r][c];
                        int nr = sh.r;
                        int nc = sh.c;
                        int d = sh.d;

                        for (int s = 0; s < sh.s; s++) {
                            if (nr + dr[d] <= 0 || nr + dr[d] > R ||
                                nc + dc[d] <= 0 || nc + dc[d] > C) {
                                d = (d % 2 == 0) ? d - 1 : d + 1; // 방향 반전
                            }
                            nr += dr[d];
                            nc += dc[d];
                        }

                        sh.r = nr;
                        sh.c = nc;
                        sh.d = d;

                        if (newMap[nr][nc] == null || newMap[nr][nc].z < sh.z) {
                            newMap[nr][nc] = sh;
                        }
                    }
                }
            }
            map = newMap; // 이동 결과 반영
        }

        System.out.println(answer);
    }
}