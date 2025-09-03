import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M;
    static Shark[][] map;
    static int[] dr = {0, -1, 1, 0, 0}; // 1:위, 2:아래, 3:오른쪽, 4:왼쪽
    static int[] dc = {0, 0, 0, 1, -1};

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
            moveSharks();
        }

        System.out.println(answer);
    }

    static void moveSharks() {
        Shark[][] newMap = new Shark[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] != null) {
                    Shark sh = map[i][j];
                    int r = sh.r;
                    int c = sh.c;
                    int d = sh.d;
                    int move = sh.s;

                    while (move-- > 0) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (nr < 1 || nr > R || nc < 1 || nc > C) {
                            d = (d % 2 == 0) ? d - 1 : d + 1; // 방향 반전
                            nr = r + dr[d]; // 반전한 방향으로 재계산
                            nc = c + dc[d];
                        }
                        r = nr;
                        c = nc;
                    }

                    sh.r = r;
                    sh.c = c;
                    sh.d = d;

                    if (newMap[r][c] == null || newMap[r][c].z < sh.z) {
                        newMap[r][c] = sh;
                    }
                }
            }
        }
        map = newMap; // 이동 결과 반영
    }

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
}