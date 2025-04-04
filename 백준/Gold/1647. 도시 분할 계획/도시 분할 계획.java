import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Edge>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Edge(B, C));
            graph.get(B).add(new Edge(A, C));
        }

        int sum = 0, max = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int node = cur.node;

            if (visited[node]) continue;

            visited[node] = true;
            sum += cur.cost;
            max = Math.max(max, cur.cost);

            for (Edge e : graph.get(node)) {
                if (!visited[e.node]) {
                    pq.add(e);
                }
            }
        }

        System.out.println(sum - max);
    }

    static class Edge implements Comparable<Edge> {
        int node, cost;

        Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}