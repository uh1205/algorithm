import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int v, cost;
        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N, M, X;
    static List<List<Node>> graph;
    static List<List<Node>> reverseGraph;

    static final int INF = 1000000000; // 충분히 큰 값

    public static int[] dijkstra(List<List<Node>> g, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.v, cost = cur.cost;
            if (cost > dist[u]) continue; // 이미 최적화된 경우 스킵

            for (Node next : g.get(u)) {
                int v = next.v, newCost = cost + next.cost;
                if (newCost < dist[v]) {
                    dist[v] = newCost;
                    pq.offer(new Node(v, newCost));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        // 그래프 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, t));
            reverseGraph.get(v).add(new Node(u, t)); // 역방향 그래프
        }

        // 다익스트라 2번 실행
        int[] toX = dijkstra(graph, X);
        int[] fromX = dijkstra(reverseGraph, X);

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, toX[i] + fromX[i]);
        }

        System.out.println(maxTime);
    }
}