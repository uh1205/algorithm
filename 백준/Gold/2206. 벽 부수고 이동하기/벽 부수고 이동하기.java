import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y, dist, broken;
        Node(int x, int y, int dist, int broken) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new int[N][M][2]; // 벽을 안 부순 경우(0), 부순 경우(1)

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, 0)); // 시작점 (1,1) → (0,0)
        visited[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // 도착하면 최단 거리 반환
            if (cur.x == N - 1 && cur.y == M - 1) {
                return cur.dist;
            }

            // 네 방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 범위 밖이면 무시
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                // 벽이 아닌 경우 (이동 가능)
                if (map[nx][ny] == 0 && visited[nx][ny][cur.broken] == 0) {
                    visited[nx][ny][cur.broken] = 1;
                    queue.add(new Node(nx, ny, cur.dist + 1, cur.broken));
                }
                
                // 벽이 있는 경우 (한 번만 부술 수 있음)
                if (map[nx][ny] == 1 && cur.broken == 0 && visited[nx][ny][1] == 0) {
                    visited[nx][ny][1] = 1;
                    queue.add(new Node(nx, ny, cur.dist + 1, 1)); // 벽 부수고 이동
                }
            }
        }

        return -1; // 도착할 수 없는 경우
    }
}