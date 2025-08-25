import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(meltCheese());
    }

    static int meltCheese() {
        int time = 0;
        Queue<Point> cheeseQ = new LinkedList<>();

        while (true) {
            visited = new boolean[N][M];

            // 1. 외부 공기 확장
            bfsAir();

            // 2. 녹을 치즈 찾기 (외부 공기와 2번 이상 접촉한 치즈)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && isMeltingCheese(i, j)) {
                        cheeseQ.add(new Point(i, j));
                    }
                }
            }

            // 녹일 치즈가 없다면 이미 다 녹은 것
            if (cheeseQ.isEmpty()) break;

            // 3. 치즈 녹이기
            while (!cheeseQ.isEmpty()) {
                Point cur = cheeseQ.poll();
                map[cur.r][cur.c] = 0;
            }

            time++;
        }

        return time;
    }

    static void bfsAir() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (isOut(nr, nc) || visited[nr][nc]) continue;
                if (map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc));
                }
            }
        }
    }

    static boolean isMeltingCheese(int r, int c) {
        int airCnt = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (visited[nr][nc]) airCnt++; // 외부 공기만 방문했기 때문
        }
        return airCnt >= 2;
    }

    static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}