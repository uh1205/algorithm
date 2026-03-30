import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        // 1. 인접 리스트 구성
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int[] e : edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        // 2. BFS 준비 (거리 저장 배열 및 큐)
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1); // 방문하지 않은 노드는 -1
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        dist[1] = 0; // 시작점 거리는 0

        int maxDist = 0;

        // 3. BFS 실행
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adj[cur]) {
                if (dist[next] == -1) { // 아직 방문하지 않은 노드라면
                    dist[next] = dist[cur] + 1;
                    maxDist = Math.max(maxDist, dist[next]);
                    q.offer(next);
                }
            }
        }

        // 4. 최대 거리와 일치하는 노드 개수 세기
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == maxDist) answer++;
        }

        return answer;
    }
}