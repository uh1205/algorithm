import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] visited;
    static int[][] dp;
    static List<List<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        visited = new boolean[N + 1];
        dp = new int[N + 1][2]; // [0] = 얼리어답터가 아님, [1] = 얼리어답터임

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0; // 얼리어답터가 아닐 때
        dp[node][1] = 1; // 얼리어답터일 때

        for (int next : tree.get(node)) {
            if (!visited[next]) {
                dfs(next);
                // 현재 노드가 얼리어답터가 아니면 자식이 무조건 얼리어답터
                dp[node][0] += dp[next][1];
                // 현재 노드가 얼리어답터면 자식은 상관 없음
                dp[node][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}
