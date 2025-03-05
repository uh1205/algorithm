import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while (true) {
            if (map[r][c] == 0) {
                map[r][c] = -1;
                cnt++;
            }

            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                d = (d == 0) ? 3 : d - 1;
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (map[nr][nc] == 0) {
                    flag = true;
                    r = nr;
                    c = nc;
                    break;
                }
            }

            if (!flag) {
                int nd = (d < 2) ? d + 2 : d - 2;
                int nr = r + dr[nd];
                int nc = c + dc[nd];
                if (map[nr][nc] == 1) {
                    break;
                }
                r = nr;
                c = nc;
            }
        }

        System.out.println(cnt);
    }
}