import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H;
    static int[][][] box;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        box = new int[M][N][H];
        Queue<Tomato> q = new ArrayDeque<>();

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    box[m][n][h] = Integer.parseInt(st.nextToken());
                    if (box[m][n][h] == 1) {
                        q.add(new Tomato(m, n, h)); // 익은 토마토는 BFS 시작점
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            Tomato t = q.poll();

            for (int d = 0; d < 6; d++) {
                int nx = t.x + dx[d];
                int ny = t.y + dy[d];
                int nz = t.z + dz[d];

                if (nx < 0 || ny < 0 || nz < 0 || nx >= M || ny >= N || nz >= H) continue;

                if (box[nx][ny][nz] == 0) {
                    box[nx][ny][nz] = box[t.x][t.y][t.z] + 1;
                    q.add(new Tomato(nx, ny, nz));
                }
            }
        }

        int maxDay = 0;
        for (int m = 0; m < M; m++) {
            for (int n = 0; n < N; n++) {
                for (int h = 0; h < H; h++) {
                    if (box[m][n][h] == 0) {
                        System.out.println(-1); // 익지 않은 토마토 남음
                        return;
                    }
                    maxDay = Math.max(maxDay, box[m][n][h]);
                }
            }
        }

        System.out.println(maxDay - 1); // 1부터 시작했으므로 하루 빼줌
    }

    static class Tomato {
        int x, y, z;

        public Tomato(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}