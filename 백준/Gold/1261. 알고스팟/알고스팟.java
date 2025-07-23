import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int R, C;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Deque<Edge> dq = new ArrayDeque<>();
        dq.add(new Edge(0, 0, 0));
        visited[0][0] = true;

        while (!dq.isEmpty()) {
            Edge cur = dq.poll();
            int cr = cur.r;
            int cc = cur.c;
            int cw = cur.w;

            if (cr == R - 1 && cc == C - 1) {
                return cw;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;

                if (map[nr][nc] == 0) {
                    dq.addFirst(new Edge(nr, nc, cw));
                } else {
                    dq.addLast(new Edge(nr, nc, cw + 1));
                }
            }
        }

        return -1;
    }

    static class Edge {
        int r, c, w;

        public Edge(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }
}