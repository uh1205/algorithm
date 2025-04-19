import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 200_000_000;
    static int N, E;
    static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N + 1);
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

        int[] dist1 = dijkstra(1);
        int[] distV1 = dijkstra(v1);
        int[] distV2 = dijkstra(v2);

        int path1 = addIfPossible(dist1[v1], distV1[v2], distV2[N]);  // 1 → v1 → v2 → N
        int path2 = addIfPossible(dist1[v2], distV2[v1], distV1[N]);  // 1 → v2 → v1 → N

        int answer = Math.min(path1, path2);
        System.out.println(answer == INF ? -1 : answer);
    }

    static int addIfPossible(int... values) {
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
                int nextDist = cur.dist + next.dist;
                if (nextDist < dist[next.node]) {
                    dist[next.node] = nextDist;
                    pq.offer(new Edge(next.node, nextDist));
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
            return Integer.compare(this.dist, o.dist);
        }
    }
}