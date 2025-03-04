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
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 낮은 값부터 나옴
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j]) {
                    cnt++;
                    pq.add(dfs(i, j));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append('\n');
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append('\n');
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