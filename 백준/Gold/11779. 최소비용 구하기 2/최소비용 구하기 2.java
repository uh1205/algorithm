import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Edge>> graph;
    static int[] dist, prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시 개수
        M = Integer.parseInt(br.readLine()); // 버스 개수

        graph = new ArrayList<>();
        dist = new int[N + 1];
        prev = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        StringBuilder result = new StringBuilder();
        result.append(dist[end]).append('\n');

        // 경로 역추적
        Stack<Integer> path = new Stack<>();
        int cur = end;
        while (cur != 0) {
            path.push(cur);
            cur = prev[cur];
        }
        result.append(path.size()).append('\n'); // 경로에 포함된 도시 수

        while (!path.isEmpty()) {
            result.append(path.pop()).append(' ');
        }

        System.out.println(result);
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;

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
                    prev[nn] = cn;
                    pq.add(new Edge(nn, nc));
                }
            }
        }
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