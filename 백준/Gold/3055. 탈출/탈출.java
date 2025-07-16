import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Point> water = new LinkedList<>();
    static Queue<Point> hog = new LinkedList<>();

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static class Point {
        int x, y, time;
        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '*') {
                    water.offer(new Point(i, j, 0));
                } else if (map[i][j] == 'S') {
                    hog.offer(new Point(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int result = bfs();
        System.out.println(result == -1 ? "KAKTUS" : result);
    }

    static int bfs() {
        while (!hog.isEmpty()) {
            // 1. 먼저 물을 확장
            int waterSize = water.size();
            while (waterSize-- > 0) {
                Point cur = water.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if (isIn(nx, ny) && map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        water.offer(new Point(nx, ny, 0));
                    }
                }
            }

            // 2. 고슴도치 이동
            int hogSize = hog.size();
            while (hogSize-- > 0) {
                Point cur = hog.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (!isIn(nx, ny) || visited[nx][ny]) continue;

                    if (map[nx][ny] == 'D') {
                        return cur.time + 1;
                    }

                    if (map[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        hog.offer(new Point(nx, ny, cur.time + 1));
                    }
                }
            }
        }
        return -1;
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}