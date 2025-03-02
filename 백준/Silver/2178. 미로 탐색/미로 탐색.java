import java.io.*;
import java.util.*;

class Main {

    static int N;
    static int M;
    static int[][] map;
    static int[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) == '1' ? 0 : 1;
            }
        }
        bfs(0, 0);
        System.out.println(visited[N - 1][M - 1]);
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});

        visited[r][c] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = curR + dr[i];
                int nc = curC + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (map[nr][nc] == 1) continue;
                if (visited[nr][nc] > 0) continue;

                visited[nr][nc] = visited[curR][curC] + 1;
                q.add(new int[]{nr, nc});
            }
        }
    }
}
