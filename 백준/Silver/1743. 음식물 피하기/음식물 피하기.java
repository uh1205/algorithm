import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int M;
    static int K;
    
    static int[][] map;
    static int[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r - 1][c - 1] = 1;
        }

        max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && visited[i][j] == 0) {
                    max = Math.max(max, bfs(i, j));
                }
            }
        }
        System.out.println(max);
    }

    static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});

        visited[r][c] = 1;

        int cnt = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nr = curR + dr[i];
                int nc = curC + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (map[nr][nc] == 0) continue;
                if (visited[nr][nc] > 0) continue;

                visited[nr][nc] = 1;
                q.add(new int[]{nr, nc});
            }
        }
        return cnt;
    }
}
