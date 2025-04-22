import java.io.*;
import java.util.*;

public class Main {
    static int V, farthestNode, maxDist;
    static boolean[] visited;
    static List<List<Edge>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        for (int i = 0; i < V + 1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            tree.get(from).add(new Edge(to, dist));

            while ((to = Integer.parseInt(st.nextToken())) != -1) {
                dist = Integer.parseInt(st.nextToken());
                tree.get(from).add(new Edge(to, dist));
            }
        }

        // 1번 노드에서 가장 먼 노드 찾기
        visited = new boolean[V + 1];
        dfs(1, 0);

        // 해당 노드에서 가장 먼 노드까지의 거리 구하기
        maxDist = 0;
        visited = new boolean[V + 1];
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int start, int dist) {
        visited[start] = true;

        for (Edge next : tree.get(start)) {
            if (visited[next.node]) continue;

            int nextDist = dist + next.dist;
            if (nextDist > maxDist) {
                maxDist = nextDist;
                farthestNode = next.node;
            }

            dfs(next.node, nextDist);
        }
    }

    static class Edge {
        int node, dist;

        public Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}