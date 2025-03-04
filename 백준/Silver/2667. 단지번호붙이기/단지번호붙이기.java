import java.io.*;
import java.util.*;

class Main {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static int N;
    static int[][] map;
    static boolean[][] visited;

    static List<Integer> counts = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) == '0' ? 0 : 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    counts.add(dfs(i, j));
                }
            }
        }
        Collections.sort(counts);

        StringBuilder sb = new StringBuilder();
        sb.append(counts.size()).append("\n");
        counts.forEach(c -> sb.append(c).append("\n"));

        System.out.println(sb);
    }

    static int dfs(int r, int c) {
        int cnt = 1;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            if (map[nr][nc] == 1 && !visited[nr][nc]) {
                visited[nr][nc] = true;
                cnt += dfs(nr, nc);
            }
        }
        return cnt;
    }

}