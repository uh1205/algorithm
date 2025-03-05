import java.io.*;
import java.util.*;

class Main {

    static int N, M, r, c, d, cnt = 0;

    static int[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(r, c);
        System.out.println(cnt);
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];

            if (map[cr][cc] == 0) {
                cnt++;
                map[cr][cc] = -1; // 청소됨
            }

            boolean zero = false;
            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if (map[nr][nc] == 0) {
                    zero = true;
                    break;
                }
            }

            if (zero) {
                while (true) {
                    d = (d == 0) ? 3 : d - 1; // 반시계 90도 회전
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];
                    
                    if (map[nr][nc] == 0) {
                        q.add(new int[]{nr, nc});
                        break;
                    }
                }
            } else {
                int nd = (d < 2) ? d + 2 : d - 2; // 후진
                int nr = cr + dr[nd];
                int nc = cc + dc[nd];

                if (map[nr][nc] == 1) {
                    return;
                }
                q.add(new int[]{nr, nc});
            }
        }
    }

}