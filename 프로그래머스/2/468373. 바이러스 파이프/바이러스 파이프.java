import java.util.*;

class Solution {
    int maxInfected = 0;
    int N;
    List<Edge>[] adj;

    class Edge {
        int to, type;
        Edge(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }

    public int solution(int n, int infection, int[][] edges, int k) {
        this.N = n;
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int t = edge[2];
            adj[u].add(new Edge(v, t));
            adj[v].add(new Edge(u, t));
        }

        // 초기 감염 상태 (1번부터 n번까지 사용)
        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;

        dfs(0, k, infected, 1); // 초기 감염자 수 1명부터 시작

        return maxInfected;
    }

    private void dfs(int count, int k, boolean[] infected, int currentSum) {
        // 결과 업데이트
        maxInfected = Math.max(maxInfected, currentSum);

        // 기저 조건: k번 모두 수행했거나 모든 배양체가 감염된 경우
        if (count == k || currentSum == N) {
            return;
        }

        // 3종류의 파이프(1:A, 2:B, 3:C)를 각각 열어보는 시도
        for (int type = 1; type <= 3; type++) {
            boolean[] nextInfected = infected.clone();
            int nextSum = spread(nextInfected, type, currentSum);
            
            // 변화가 있는 경우에만 다음 단계로 진행 (효율성)
            if (nextSum > currentSum) {
                dfs(count + 1, k, nextInfected, nextSum);
            } else {
                // 변화가 없더라도 횟수는 차감되므로 depth는 늘려야 하지만, 
                // 이 문제에서는 의미 없는 행동이 되므로 넘어가도 무방합니다.
                // 단, k번을 반드시 채워야 하는 제약은 없으므로 체크하지 않아도 결과는 같습니다.
            }
        }
    }

    // 특정 타입의 파이프를 열었을 때 감염을 확산시키는 함수 (BFS)
    private int spread(boolean[] infected, int type, int currentSum) {
        Queue<Integer> queue = new LinkedList<>();
        
        // 현재 감염된 모든 노드를 시작점으로 설정
        for (int i = 1; i <= N; i++) {
            if (infected[i]) {
                queue.offer(i);
            }
        }

        int newInfectedCount = currentSum;
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (Edge edge : adj[curr]) {
                // 파이프 종류가 일치하고, 아직 감염되지 않은 노드라면
                if (edge.type == type && !infected[edge.to]) {
                    infected[edge.to] = true;
                    newInfectedCount++;
                    queue.offer(edge.to); // 새롭게 감염된 노드로부터 다시 확산
                }
            }
        }
        return newInfectedCount;
    }
}