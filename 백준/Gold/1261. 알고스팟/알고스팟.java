import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int R, C;
    static int[][] map, dist;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        dist = new int[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j) - '0';
                dist[i][j] = INF;
            }
        }

        dijkstra();

        System.out.println(dist[R - 1][C - 1]);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int cr = cur.r;
            int cc = cur.c;
            int cw = cur.w;

            if (cw > dist[cr][cc]) continue;

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;

                int nw = cw + map[nr][nc];

                if (nw < dist[nr][nc]) {
                    dist[nr][nc] = nw;
                    pq.add(new Edge(nr, nc, nw));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int r, c, w;

        public Edge(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}