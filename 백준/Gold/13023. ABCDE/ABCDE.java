import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            dfs(1, i);
            if (found) break;
        }

        System.out.println(found ? 1 : 0);
    }

    static void dfs(int depth, int node) {
        if (found) return; // 이미 찾은 경우 중단

        if (depth == 5) {
            found = true;
            return;
        }

        visited[node] = true;
        for (int next : graph.get(node)) {
            if (visited[next]) continue;
            dfs(depth + 1, next);
        }
        visited[node] = false; // 다른 경로 탐색을 위해 백트래킹
    }
}