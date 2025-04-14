import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> graph;
    static int[] color;
    static boolean isBipartite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i < V + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            color = new int[V + 1]; // -1: blue, 0: unvisited, 1: red
            isBipartite = true;

            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) {
                    bfs(i);
                }
            }

            sb.append(isBipartite ? "YES" : "NO").append('\n');
        }

        System.out.println(sb);
    }

    static void bfs(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        color[node] = 1; // 시작은 red

        while (!q.isEmpty() && isBipartite) {
            int cur = q.poll();

            for (int next : graph.get(cur)) {
                if (color[next] == 0) {
                    color[next] = -color[cur]; // 반대 색깔로 칠하기
                    q.add(next);
                } else if (color[next] == color[cur]) {
                    isBipartite = false;
                    return;
                }
            }
        }
    }
}