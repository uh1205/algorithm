import java.io.*;
import java.util.*;

public class Main {
    static int N, M, islandCount = 0;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 지도 입력
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호 붙임
        markIslands();

        // 각 섬을 잇는 다리의 최소 길이(최소 2 이상) 구하기
        int[][] distance = new int[islandCount + 1][islandCount + 1];
        for (int[] d : distance) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    buildBridge(i, j, map[i][j], distance);
                }
            }
        }

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < islandCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length; j++) {
                if (distance[i][j] != Integer.MAX_VALUE) {
                    graph.get(i).add(new Edge(j, distance[i][j]));
                }
            }
        }

        // MST
        boolean[] visited = new boolean[islandCount + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));

        int sum = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int node = cur.node;

            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            sum += cur.dist;

            if (++count == islandCount) {
                break;
            }

            for (Edge e : graph.get(node)) {
                if (!visited[e.node]) {
                    pq.add(e);
                }
            }
        }

        if (count != islandCount) {
            System.out.println(-1);
            return;
        }

        System.out.println(sum);
    }

    static void markIslands() {
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j, ++islandCount, visited);
                }
            }
        }
    }

    static void bfs(int i, int j, int num, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;
        map[i][j] = num;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M ||
                        visited[nr][nc] || map[nr][nc] == 0) {
                    continue;
                }

                q.add(new int[]{nr, nc});
                visited[nr][nc] = true;
                map[nr][nc] = num;
            }
        }
    }

    // 시작 섬에서 다른 섬으로 길이가 최소 2 이상인 다리 짓기
    static void buildBridge(int i, int j, int start, int[][] distance) {
        for (int d = 0; d < 4; d++) {
            int r = i;
            int c = j;
            int dist = 0;

            while (true) {
                r += dr[d];
                c += dc[d];

                if (r < 0 || c < 0 || r >= N || c >= M) {
                    break;
                }
                if (map[r][c] == start) { // 시작 섬
                    break;
                }
                if (map[r][c] != 0) { // 다른 섬 도착
                    if (dist >= 2) {
                        distance[start][map[r][c]] = Math.min(
                                distance[start][map[r][c]],
                                dist
                        );
                    }
                    break;
                }
                dist++;
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int node;
        int dist;

        public Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}
