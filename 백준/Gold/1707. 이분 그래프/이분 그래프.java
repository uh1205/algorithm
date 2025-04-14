import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> graph;
    static int[] color;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            color = new int[V + 1]; // 0: 방문 전, 1: 빨강, -1: 파랑
            boolean isBipartite = true;

            for (int i = 1; i <= V && isBipartite; i++) {
                if (color[i] == 0) {
                    if (!bfs(i)) {
                        isBipartite = false;
                    }
                }
            }

            sb.append(isBipartite ? "YES" : "NO").append('\n');
        }

        System.out.print(sb);
    }

    static boolean bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (color[next] == 0) {
                    color[next] = -color[cur];
                    queue.offer(next);
                } else if (color[next] == color[cur]) {
                    return false;
                }
            }
        }

        return true;
    }
}