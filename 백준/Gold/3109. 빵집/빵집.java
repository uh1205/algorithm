import java.io.*;
import java.util.*;

class Main {

    static int R;
    static int C;

    static int[][] map;
    static int[][] visited;

    static int[] dr = {-1, 0, 1};
    static int[] dc = {1, 1, 1};

    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new int[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j) == '.' ? 0 : 1;
            }
        }
        cnt = 0;
        for (int i = 0; i < R; i++) {
            dfs(i, 0);
        }
        System.out.println(cnt);
    }

    static boolean dfs(int r, int c) {
        if (c == C - 1) {
            cnt++;
            return true;
        }
        visited[r][c] = 1;
        for (int i = 0; i < 3; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
            if (map[nr][nc] == 1) continue;
            if (visited[nr][nc] == 1) continue;
            if (dfs(nr, nc)) return true;
        }
        return false;
    }
}