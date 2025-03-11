import java.io.*;
import java.util.*;

public class Main {

    static int N, rain, result = 1; // 하나도 안 잠기는 경우
    static int min = Integer.MAX_VALUE, max = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        for (rain = min; rain < max; rain++) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] > rain && !visited[i][j]) {
                        cnt++;
                        dfs(i, j);
                    }
                }
            }
            result = Math.max(result, cnt);
            visited = new boolean[N][N];
        }

        System.out.println(result);
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            if (map[nr][nc] > rain && !visited[nr][nc]) {
                dfs(nr, nc);
            }
        }
    }

}