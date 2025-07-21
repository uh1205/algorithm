import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dist;
    static List<List<Edge>> graph;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 0;
        int N;

        while ((N = Integer.parseInt(br.readLine())) != 0) {
            T++;
            map = new int[N][N];
            dist = new int[N * N];
            graph = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    dist[N * r + c] = INF;
                    graph.add(new ArrayList<>());
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int now = N * r + c;

                    if (c + 1 < N) { // 오른쪽
                        graph.get(now).add(new Edge(now + 1, map[r][c + 1]));
                    }

                    if (r + 1 < N) { // 아래
                        graph.get(now).add(new Edge(now + N, map[r + 1][c]));
                    }

                    if (c - 1 >= 0) { // 왼쪽
                        graph.get(now).add(new Edge(now - 1, map[r][c - 1]));
                    }

                    if (r - 1 >= 0) { // 위
                        graph.get(now).add(new Edge(now - N, map[r - 1][c]));
                    }
                }
            }

            dijkstra();

            sb.append("Problem ").append(T).append(": ");
            sb.append(dist[N * N - 1]).append('\n');
        }

        System.out.println(sb);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, map[0][0]));
        dist[0] = map[0][0];

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int cn = cur.node;
            int cw = cur.weight;

            if (cw > dist[cn]) continue;

            for (Edge e : graph.get(cn)) {
                int nn = e.node;
                int nw = cw + e.weight;

                if (nw < dist[nn]) {
                    dist[nn] = nw;
                    pq.add(new Edge(nn, nw));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}