import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean[] visited = new boolean[n]; // 방문 여부
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] p : path) {
            graph.get(p[0]).add(p[1]);
            graph.get(p[1]).add(p[0]);
        }

        int[] before = new int[n]; // 특정 방을 방문하려면 먼저 방문해야 하는 방
        Arrays.fill(before, -1);
        for (int[] o : order) {
            before[o[1]] = o[0];
        }

        Map<Integer, Integer> waiting = new HashMap<>(); // 대기중인 방들

        Queue<Integer> q = new ArrayDeque<>();
        if (before[0] != -1) return false; // 시작 방이 누군가의 후순위라면 불가능

        q.add(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph.get(now)) {
                if (visited[next]) continue;

                int bn = before[next]; // next 방을 방문하기 위해 먼저 방문해야 할 방
                if (bn != -1 && !visited[bn]) { // 선행할 방이 있으면서, 아직 선행 방을 안 갔으면
                    waiting.put(bn, next); // 대기시킴
                    continue;
                }

                visited[next] = true;
                q.add(next);
            }

            // 내가 누군가의 선행 방이라면 (누가 날 방문하기를 기다리고 있다면)
            if (waiting.containsKey(now)) {
                int target = waiting.get(now); // 기다리고 있는 방
                q.add(target);
                visited[target] = true;
                waiting.remove(now); // 웨이팅 목록에서 제거
            }
        }

        for (boolean v : visited) {
            if (!v) return false; // 방문하지 않은 방이 있다면 실패
        }

        return true;
    }
}