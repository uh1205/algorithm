import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 집 개수
        int M = Integer.parseInt(st.nextToken()); // 길 개수

        PriorityQueue<Edge> edges = new PriorityQueue<>();
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
        }

//        Collections.sort(edges); // 비용 기준 오름차순 정렬

        int total = 0;
        int maxEdge = 0;

        while (!edges.isEmpty()) {
            Edge cur = edges.poll();
            int a = find(cur.from);
            int b = find(cur.to);
            if (a != b) {
                if (a < b) parent[a] = b;
                else parent[b] = a;
                total += cur.cost;
                maxEdge = Math.max(maxEdge, cur.cost); // MST에서 가장 큰 간선 저장
            }
        }

        System.out.println(total - maxEdge); // 가장 비싼 간선 제거
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
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

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}