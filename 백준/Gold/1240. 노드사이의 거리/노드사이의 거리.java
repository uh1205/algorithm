import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<Node>> tree = new ArrayList<>();
    static int[] parent, depth, dist;
    static boolean[] visited;

    static class Node {
        int to, cost;
        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리 정보 입력
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree.get(a).add(new Node(b, c));
            tree.get(b).add(new Node(a, c));
        }

        parent = new int[N + 1];
        depth = new int[N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        // 루트(1)에서 DFS 시작
        dfs(1, 0, 0);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int lca = findLCA(a, b);
            int distance = dist[a] + dist[b] - 2 * dist[lca];
            sb.append(distance).append('\n');
        }

        System.out.print(sb);
    }

    // DFS로 depth, parent, dist 계산
    static void dfs(int cur, int d, int total) {
        visited[cur] = true;
        depth[cur] = d;
        dist[cur] = total;
        for (Node next : tree.get(cur)) {
            if (!visited[next.to]) {
                parent[next.to] = cur;
                dfs(next.to, d + 1, total + next.cost);
            }
        }
    }

    // LCA 찾기 (단순히 부모 타고 올라가기)
    static int findLCA(int a, int b) {
        // 깊이 맞추기
        while (depth[a] > depth[b]) a = parent[a];
        while (depth[b] > depth[a]) b = parent[b];

        // 위로 같이 올라가며 만나는 지점 찾기
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
}