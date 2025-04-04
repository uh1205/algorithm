import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i < N + 1; i++) parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            pq.add(new Edge(from, to, cost));
        }

        int total = 0, maxEdge = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            int a = find(cur.from);
            int b = find(cur.to);
            
            if (a != b) {
                if (a < b) parent[a] = b;
                else parent[b] = a;
                total += cur.cost;
                maxEdge = Math.max(maxEdge, cur.cost);
            }
        }

        System.out.println(total - maxEdge);
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}