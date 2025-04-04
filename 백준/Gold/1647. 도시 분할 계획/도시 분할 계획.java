import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 집 개수
        int M = Integer.parseInt(st.nextToken()); // 길 개수

        List<Edge> edges = new ArrayList<>();
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
        }

        Collections.sort(edges); // 비용 기준 오름차순 정렬

        int total = 0;
        int maxEdge = 0;

        for (Edge e : edges) {
            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                total += e.cost;
                maxEdge = Math.max(maxEdge, e.cost); // MST에서 가장 큰 간선 저장
            }
        }

        System.out.println(total - maxEdge); // 가장 비싼 간선 제거
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