import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int node, weight;
    
    Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

class Main {
    
    static int N, M, X;
    static List<List<Edge>> graph;
    static List<List<Edge>> reverseGraph;

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

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, t));
            reverseGraph.get(v).add(new Edge(u, t)); // 역방향 그래프 생성
        }

        int[] toX = dijkstra(graph, X);
        int[] fromX = dijkstra(reverseGraph, X);

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, toX[i] + fromX[i]);
        }

        System.out.println(max);
    }

    static int[] dijkstra(List<List<Edge>> g, int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int cn = cur.node;
            int cw = cur.weight;
            
            if (cw > dist[cn]) continue;

            for (Edge e : g.get(cn)) {
                int nn = e.node;
                int nw = cw + e.weight;
                if (dist[nn] > nw) {
                    dist[nn] = nw;
                    pq.add(new Edge(nn, nw));
                }
            }
        }
        
        return dist;
    }
}