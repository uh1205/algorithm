import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] p : path) {
            graph.get(p[0]).add(p[1]);
            graph.get(p[1]).add(p[0]);
        }

        // order 조건
        int[] before = new int[n]; // 특정 방을 방문하려면 먼저 방문해야 하는 방
        Arrays.fill(before, -1);
        for (int[] o : order) {
            before[o[1]] = o[0];
        }

        // 방문 여부
        boolean[] visited = new boolean[n];
        // 대기중인 방들
        Map<Integer, Integer> waiting = new HashMap<>();

        Queue<Integer> q = new ArrayDeque<>();
        if (before[0] != -1) return false; // 시작 방이 누군가의 후순위라면 불가능

        q.offer(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph.get(now)) {
                if (visited[next]) continue;

                if (before[next] != -1 && !visited[before[next]]) {
                    // 아직 선행 방 안 갔으면 대기시킴
                    waiting.put(before[next], next);
                    continue;
                }

                visited[next] = true;
                q.offer(next);
            }

            // 내가 누군가의 선행 방이라면, 그 방이 대기 중인지 확인
            if (waiting.containsKey(now)) {
                int target = waiting.get(now);
                q.offer(target);
                visited[target] = true;
                waiting.remove(now);
            }
        }

        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }
}