import java.io.*;
import java.util.*;

public class Main {
    static int N, M, islandCount = 0;
    static int[][] map;
    static int[][] adj;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 섬 식별 (BFS)
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    labelIsland(i, j, ++islandCount, visited);
                }
            }
        }

        // 2. 인접 행렬 초기화 (무한대 값으로)
        adj = new int[islandCount + 1][islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            Arrays.fill(adj[i], Integer.MAX_VALUE);
        }

        // 3. 모든 다리 건설 시도 및 최소 거리 갱신
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    makeBridges(i, j, map[i][j]);
                }
            }
        }

        // 4. 프림 알고리즘 수행
        System.out.println(prim());
    }

    static void labelIsland(int r, int c, int id, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        map[r][c] = id;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (nr >= 0 && nc >= 0 && nr < N && nc < M &&
                        map[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    map[nr][nc] = id;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }

    static void makeBridges(int r, int c, int startIsland) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            int dist = 0;

            while (nr >= 0 && nc >= 0 && nr < N && nc < M) {
                if (map[nr][nc] == startIsland) {
                    break; // 같은 섬
                }
                if (map[nr][nc] > 0) { // 다른 섬 도달
                    if (dist >= 2) {
                        int endIsland = map[nr][nc];
                        adj[startIsland][endIsland] = Math.min(adj[startIsland][endIsland], dist);
                        adj[endIsland][startIsland] = Math.min(adj[endIsland][startIsland], dist);
                    }
                    break;
                }
                // 바다일 경우 계속 전진
                dist++;
                nr += dr[d];
                nc += dc[d];
            }
        }
    }

    static int prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] inMST = new boolean[islandCount + 1];
        pq.add(new Node(1, 0)); // 1번 섬부터 시작

        int totalWeight = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (inMST[curr.to]) {
                continue;
            }

            inMST[curr.to] = true;
            totalWeight += curr.weight;
            count++;

            for (int next = 1; next <= islandCount; next++) {
                if (!inMST[next] && adj[curr.to][next] != Integer.MAX_VALUE) {
                    pq.add(new Node(next, adj[curr.to][next]));
                }
            }
        }

        // 모든 섬이 연결되었는지 확인
        return count == islandCount ? totalWeight : -1;
    }

    static class Node implements Comparable<Node> {
        int to, weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
