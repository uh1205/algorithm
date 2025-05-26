import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H;
    static int[][][] box;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    static class Point {
        int x, y, z;

        Point(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        box = new int[H][N][M];
        visited = new boolean[H][N][M];

        Queue<Point> queue = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(st.nextToken());
                    if (box[h][n][m] == 1) {
                        queue.offer(new Point(h, n, m)); // 익은 토마토 초기 위치
                        visited[h][n][m] = true;
                    }
                }
            }
        }

        int days = bfs(queue);

        // 토마토가 모두 익었는지 확인
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(days);
    }

    static int bfs(Queue<Point> queue) {
        int day = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            day++;

            for (int s = 0; s < size; s++) {
                Point p = queue.poll();

                for (int d = 0; d < 6; d++) {
                    int nz = p.z + dz[d];
                    int ny = p.y + dy[d];
                    int nx = p.x + dx[d];

                    if (nz < 0 || nz >= H || ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                    if (visited[nz][ny][nx]) continue;
                    if (box[nz][ny][nx] != 0) continue;

                    box[nz][ny][nx] = 1;
                    visited[nz][ny][nx] = true;
                    queue.offer(new Point(nz, ny, nx));
                }
            }
        }

        return day;
    }
}