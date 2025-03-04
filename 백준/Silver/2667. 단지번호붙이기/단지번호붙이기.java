import java.io.*;
import java.util.*;

class Main {

    static int N;
    static boolean[][] map;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) == '1';
            }
        }

        int cnt = 0;
        Queue<Integer> q = new PriorityQueue<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c]) {
                    cnt++;
                    q.offer(dfs(r, c));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append('\n');

        while (!q.isEmpty()) {
            sb.append(q.poll()).append('\n');
        }

        System.out.print(sb);
    }

    static int dfs(int r, int c) {
        map[r][c] = false;
        int size = 1;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            if (map[nr][nc]) {
                size += dfs(nr, nc);
            }
        }
        return size;
    }

}