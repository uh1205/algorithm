import java.io.*;
import java.util.StringTokenizer;

class Main {
    static int N, M, max = 0;
    static int[][] map;
    static boolean[] visited = new boolean[26];
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - 'A';
            }
        }

        visited[map[0][0]] = true;
        dfs(0, 0, 1);

        System.out.println(max);
    }

    static void dfs(int r, int c, int dist) {
        max = Math.max(max, dist);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

            int idx = map[nr][nc];
            if (visited[idx]) continue;

            visited[idx] = true;
            dfs(nr, nc, dist + 1);
            visited[idx] = false;
        }
    }
}