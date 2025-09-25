import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int[] parent;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1, -1);

        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int cur, int p) {
        parent[cur] = p;
        for (int next : graph[cur]) {
            if (next != p) { // 부모로 되돌아가는 거 방지
                dfs(next, cur);
            }
        }
    }
}