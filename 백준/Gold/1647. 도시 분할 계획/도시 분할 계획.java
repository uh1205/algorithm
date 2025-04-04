import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Edge>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

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
            sum += cur.weight;
            max = Math.max(max, cur.weight);

            for (Edge e : graph.get(node)) {
                if (!visited[e.node]) {
                    pq.add(e);
                }
            }
        }

        System.out.println(sum - max);
    }

    static class Edge implements Comparable<Edge> {
        int node, weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}