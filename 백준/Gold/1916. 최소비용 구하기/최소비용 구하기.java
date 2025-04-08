import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Edge>> graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시 개수
        M = Integer.parseInt(br.readLine()); // 버스 개수

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, cost));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        pq.add(new Edge(from, 0));
        dist[from] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int cn = cur.node;
            int cc = cur.cost;

            if (cc > dist[cn]) continue;

            for (Edge e : graph.get(cn)) {
                int nn = e.node;
                int nc = cc + e.cost;
                if (nc < dist[nn]) {
                    dist[nn] = nc;
                    pq.add(new Edge(nn, nc));
                }
            }
        }

        System.out.println(dist[to]);
    }

    static class Edge implements Comparable<Edge> {
        int node, cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}