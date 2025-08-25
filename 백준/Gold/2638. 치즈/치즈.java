import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int cheeseCount = 0;
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
                if (map[i][j] == 1) cheeseCount++;
            }
        }

        System.out.println(meltCheese());
    }

    static int meltCheese() {
        int time = 0;
        Queue<Point> airQ = new LinkedList<>();
        Queue<Point> cheeseQ = new LinkedList<>();

        while (cheeseCount > 0) {
            int[][] visited = new int[N][M];
            airQ.add(new Point(0, 0));
            visited[0][0] = 2;

            // 1. (0,0)에서 공기 확장
            while (!airQ.isEmpty()) {
                Point cur = airQ.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (isOut(nr, nc) || visited[nr][nc] == 2) continue;

                    // 공기인 경우
                    if (map[nr][nc] == 0) {
                        airQ.add(new Point(nr, nc));
                        visited[nr][nc] = 2;
                        continue;
                    }

                    // 치즈의 공기 접촉 횟수 늘리기
                    visited[nr][nc]++;

                    // 공기에 2번 접촉한 치즈 큐에 넣기
                    if (visited[nr][nc] == 2) {
                        cheeseQ.add(new Point(nr, nc));
                    }
                }
            }

            time++;

            // 2. 공기 확장 끝나면 치즈 큐에 들어있는 치즈들 녹이기
            while (!cheeseQ.isEmpty()) {
                Point cur = cheeseQ.poll();
                map[cur.r][cur.c] = 0;
                cheeseCount--;
            }
        }

        return time;
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