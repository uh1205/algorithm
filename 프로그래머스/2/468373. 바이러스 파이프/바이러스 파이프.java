import java.util.*;

class Solution {

    static class Edge {
        int to, type;
        Edge(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }

    List<Edge>[] graph;
    int n, k;
    int answer = 0;

    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.k = k;

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            int a = e[0], b = e[1], t = e[2];
            graph[a].add(new Edge(b, t));
            graph[b].add(new Edge(a, t));
        }

        Set<Integer> start = new HashSet<>();
        start.add(infection);

        dfs(0, start, 0);

        return answer;
    }

    void dfs(int depth, Set<Integer> infected, int lastType) {
        answer = Math.max(answer, infected.size());

        if (depth == k) return;

        for (int type = 1; type <= 3; type++) {
            if (type == lastType) continue;

            Set<Integer> next = spread(infected, type);

            // 변화 없으면 skip
            if (next.size() == infected.size()) continue;

            dfs(depth + 1, next, type);
        }
    }

    Set<Integer> spread(Set<Integer> infected, int type) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        for (int node : infected) {
            q.add(node);
            visited[node] = true;
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Edge e : graph[cur]) {
                if (e.type != type) continue;
                if (visited[e.to]) continue;

                visited[e.to] = true;
                q.add(e.to);
            }
        }

        Set<Integer> result = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            if (visited[i]) result.add(i);
        }

        return result;
    }
}