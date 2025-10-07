import java.io.*;
import java.util.*;

public class Main {
    static int N, LOG;
    static List<List<Edge>> tree = new ArrayList<>();
    static int[][] parent; // 2^k번째 조상
    static int[] depth;
    static long[] dist; // 루트에서 각 노드까지의 거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        LOG = (int) Math.ceil(Math.log(N) / Math.log(2));

        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree.get(a).add(new Edge(b, c));
            tree.get(b).add(new Edge(a, c));
        }

        parent = new int[LOG + 1][N + 1];
        depth = new int[N + 1];
        dist = new long[N + 1];

        dfs(1, 0, 0);

        for (int k = 1; k <= LOG; k++) {
            for (int v = 1; v <= N; v++) {
                parent[k][v] = parent[k - 1][parent[k - 1][v]];
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int lca = lca(a, b);

            long distance = dist[a] + dist[b] - 2 * dist[lca];
            sb.append(distance).append('\n');
        }

        System.out.println(sb);
    }

    static void dfs(int v, int p, long d) {
        parent[0][v] = p;
        dist[v] = d;
        for (Edge next : tree.get(v)) {
            if (next.to != p) {
                depth[next.to] = depth[v] + 1;
                dfs(next.to, v, d + next.dist);
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        int diff = depth[a] - depth[b];
        for (int k = LOG; k >= 0; k--) {
            if ((diff & (1 << k)) != 0) {
                a = parent[k][a];
            }
        }

        if (a == b) return a;

        for (int k = LOG; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        return parent[0][a];
    }

    static class Edge {
        int to, dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}