import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 200_000_000;

    static int N, E;
    static List<List<Edge>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] distFrom1 = dijkstra(1);
        int[] distFromV1 = dijkstra(v1);
        int[] distFromV2 = dijkstra(v2);

        int routeA = safeAdd(distFrom1[v1], distFromV1[v2], distFromV2[N]);
        int routeB = safeAdd(distFrom1[v2], distFromV2[v1], distFromV1[N]);

        int min = Math.min(routeA, routeB);
        System.out.println(min >= INF ? -1 : min);
    }

    static int safeAdd(int... values) {
        int sum = 0;
        for (int v : values) {
            if (v >= INF) return INF;
            sum += v;
        }
        return sum;
    }

    static int[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.node] < cur.dist) continue;

            for (Edge next : graph.get(cur.node)) {
                int newDist = cur.dist + next.dist;
                if (newDist < dist[next.node]) {
                    dist[next.node] = newDist;
                    pq.offer(new Edge(next.node, newDist));
                }
            }
        }

        return dist;
    }

    static class Edge implements Comparable<Edge> {
        int node, dist;

        Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}