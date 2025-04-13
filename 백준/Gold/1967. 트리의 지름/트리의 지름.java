import java.io.*;
import java.util.*;

public class Main {
    static int n, farthestNode, farthestDist = 0;
    static List<List<Edge>> tree = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n + 1; i++) {
            tree.add(new ArrayList<>());
        }

        visited = new boolean[n + 1];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            tree.get(p).add(new Edge(c, w));
            tree.get(c).add(new Edge(p, w));
        }

        // 1번 노드에서 가장 먼 노드 찾기
        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[n + 1];
        farthestDist = 0;

        // 해당 노드에서 가장 먼 노드 찾기
        visited[farthestNode] = true;
        dfs(farthestNode, 0);

        System.out.println(farthestDist);
    }

    static void dfs(int node, int dist) {
        for (Edge next : tree.get(node)) {
            int nn = next.node;

            if (visited[nn]) continue;
            visited[nn] = true;

            int nw = dist + next.weight;
            if (nw > farthestDist) {
                farthestNode = nn;
                farthestDist = nw;
            }
            dfs(nn, nw);
        }
    }

    private static class Edge {
        int node, weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}