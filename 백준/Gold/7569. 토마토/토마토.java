import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static int[][][] map;
    static int unripeCount = 0;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dr = {0, 1, 0, -1, 0, 0};
    static int[] dc = {1, 0, -1, 0, 0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        map = new int[N][M][H];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    int n = Integer.parseInt(st.nextToken());
                    if (n == 0) {
                        unripeCount++;
                    } else if (n == 1) {
                        q.add(new int[]{j, k, i});
                    }
                    map[j][k][i] = n;
                }
            }
        }

        if (unripeCount == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int day = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int cr = cur[0];
                int cc = cur[1];
                int ch = cur[2];

                for (int d = 0; d < 6; d++) {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];
                    int nh = ch + dh[d];

                    if (nr < 0 || nc < 0 || nh < 0 ||
                            nr >= N || nc >= M || nh >= H) {
                        continue;
                    }

                    if (map[nr][nc][nh] == 0) {
                        map[nr][nc][nh] = 1;
                        unripeCount--;
                        q.add(new int[]{nr, nc, nh});
                    }
                }
            }

            if (!q.isEmpty()) day++;
        }

        return unripeCount == 0 ? day : -1;
    }
}