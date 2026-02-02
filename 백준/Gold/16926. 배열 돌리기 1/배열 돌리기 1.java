import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) {
            arr = rotate(arr);
        }

        StringBuilder sb = new StringBuilder();
        for (int[] row : arr) {
            for (int i : row) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int[][] rotate(int[][] arr) {
        int[][] after = new int[N][M];

        int startR = 0;
        int startC = 0;
        int endR = N - 1;
        int endC = M - 1;

        int r = startR;
        int c = startC;
        int dir = 0;

        while (startR < endR && startC < endC) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (nr < startR || nc < startC || nr > endR || nc > endC) {
                dir = (dir + 1) % 4;
                continue;
            }

            after[nr][nc] = arr[r][c];
            r = nr;
            c = nc;

            if (r == startR && c == startC) {
                startR += 1;
                startC += 1;
                endR -= 1;
                endC -= 1;
                r = startR;
                c = startC;
            }
        }

        return after;
    }
}
