import java.io.*;
import java.util.*;

public class Main {
    static int N, M, LOG;
    static ArrayList<Integer>[] tree;
    static int[][] parent;
    static int[] depth;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        LOG = (int) Math.ceil(Math.log(N) / Math.log(2)); // 최대 깊이 비트 수
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        parent = new int[LOG + 1][N + 1];
        depth = new int[N + 1];
        visited = new boolean[N + 1];

        // 루트(1)에서 DFS로 깊이 & 1단계 부모 저장
        dfs(1, 0);

        // parent[k][v] = v의 2^k번째 부모 세팅
        for (int k = 1; k <= LOG; k++) {
            for (int v = 1; v <= N; v++) {
                parent[k][v] = parent[k - 1][parent[k - 1][v]];
            }
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int node, int d) {
        visited[node] = true;
        depth[node] = d;
        for (int next : tree[node]) {
            if (!visited[next]) {
                parent[0][next] = node;
                dfs(next, d + 1);
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a; a = b; b = temp;
        }

        for (int k = LOG; k >= 0; k--) {
            if (depth[a] - (1 << k) >= depth[b]) {
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