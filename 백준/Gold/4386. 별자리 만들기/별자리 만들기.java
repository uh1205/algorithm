import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Star[] stars = new Star[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Star(x, y);
        }

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
                graph.get(i).add(new Edge(j, dist));
                graph.get(j).add(new Edge(i, dist));
            }
        }

        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));

        double sum = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int node = cur.node;

            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            sum += cur.dist;
            
            if (++count == n) {
                break;
            }

            for (Edge next : graph.get(node)) {
                if (!visited[next.node]) {
                    pq.add(next);
                }
            }
        }

        System.out.printf("%.2f", sum);
    }

    static class Star {
        double x, y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int node;
        double dist;

        public Edge(int node, double dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }
}
