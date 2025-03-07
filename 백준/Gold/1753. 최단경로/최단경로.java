import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
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

class Main {

    static int V, E, K, u, v, w;
    static int[] distance;
    static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        distance = new int[V + 1];
        for (int i = 0; i < V + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Edge(v, w));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(K, 0));
        distance[K] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int n = edge.node;
            int w = edge.weight;

            if (distance[n] != w) continue;
            for (Edge e : graph.get(n)) {
                int nn = e.node;
                int nw = e.weight;
                if (distance[nn] > w + nw) {
                    distance[nn] = w + nw;
                    pq.add(new Edge(nn, distance[nn]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < V + 1; i++) {
            sb.append(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]).append('\n');
        }
        System.out.println(sb);
    }
}