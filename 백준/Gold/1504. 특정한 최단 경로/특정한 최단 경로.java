import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_DIST = 200_000_000;
    static int N, E;
    static int[] infArr, dist;
    static List<List<Edge>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        infArr = new int[N + 1];
        Arrays.fill(infArr, MAX_DIST);

        for (int i = 0; i < N + 1; i++) {
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

        // routeA: 1 -> v1 -> v2 -> N
        // routeB: 1 -> v2 -> v1 -> N
        int routeA = 0, routeB = 0;

        dijkstra(1);
        routeA += dist[v1];
        routeB += dist[v2];

        dijkstra(v1);
        routeA += dist[v2];
        routeB += dist[N];

        dijkstra(v2);
        routeA += dist[N];
        routeB += dist[v1];

        int minDist = Math.min(routeA, routeB);
        if (minDist >= MAX_DIST) System.out.println(-1);
        else System.out.println(minDist);
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist = infArr.clone();
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.dist > dist[cur.node]) continue;

            for (Edge next : graph.get(cur.node)) {
                int nextDist = cur.dist + next.dist;
                if (nextDist < dist[next.node]) {
                    dist[next.node] = nextDist;
                    pq.add(new Edge(next.node, nextDist));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int node, dist;

        public Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}