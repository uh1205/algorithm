import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            Arrays.fill(dist[i], INF);
        }

        dist[0][0] = 0;

        Deque<Node> dq = new ArrayDeque<>();
        dq.add(new Node(0, 0));

        while (!dq.isEmpty()) {
            Node cur = dq.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                int cost = map[nr][nc] == '1' ? 0 : 1;
                int nextCost = dist[cr][cc] + cost;

                if (nextCost < dist[nr][nc]) {
                    dist[nr][nc] = nextCost;
                    if (cost == 0) {
                        dq.addFirst(new Node(nr, nc));
                    } else {
                        dq.addLast(new Node(nr, nc));
                    }
                }
            }
        }

        System.out.println(dist[N - 1][N - 1]);
    }

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}