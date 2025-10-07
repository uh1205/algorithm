import java.io.*;
import java.util.*;

public class Main {
    static int N, M, LOG;
    static List<List<Integer>> tree = new ArrayList<>();
    static int[][] parent;
    static int[] depth;

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
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        parent = new int[LOG + 1][N + 1];
        depth = new int[N + 1];

        dfs(1, 0);

        for (int k = 1; k <= LOG; k++) {
            for (int v = 1; v <= N; v++) {
                parent[k][v] = parent[k - 1][parent[k - 1][v]];
            }
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append('\n');
        }

        System.out.println(sb);
    }

    static void dfs(int v, int p) {
        parent[0][v] = p;
        for (int next : tree.get(v)) {
            if (next != p) {
                depth[next] = depth[v] + 1;
                dfs(next, v);
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
}