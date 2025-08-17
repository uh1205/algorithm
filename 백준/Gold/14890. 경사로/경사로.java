import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;
    static boolean[][] installed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        // 가로 길 찾기
        installed = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            boolean passable = true;
            int prev = map[r][0];

            for (int c = 1; c < N; c++) {
                int cur = map[r][c];

                if (prev < cur) {
                    if (!checkRowSame(r, c - L, cur - 1)) {
                        passable = false;
                        break;
                    }
                } else if (prev > cur) {
                    if (!checkRowSame(r, c, prev - 1)) {
                        passable = false;
                        break;
                    }
                }
                prev = cur;
            }

            if (passable) result++;
        }

        // 세로 길 찾기
        installed = new boolean[N][N];
        for (int c = 0; c < N; c++) {
            boolean passable = true;
            int prev = map[0][c];

            for (int r = 1; r < N; r++) {
                int cur = map[r][c];

                if (prev < cur) {
                    if (!checkColumnSame(c, r - L, cur - 1)) {
                        passable = false;
                        break;
                    }
                } else if (prev > cur) {
                    if (!checkColumnSame(c, r, prev - 1)) {
                        passable = false;
                        break;
                    }
                }
                prev = cur;
            }

            if (passable) result++;
        }

        System.out.println(result);
    }

    static boolean checkRowSame(int row, int start, int height) {
        for (int i = start; i < start + L; i++) {
            if (i < 0 || i >= N) return false;
            if (map[row][i] != height) return false;
            if (installed[row][i]) return false;
            installed[row][i] = true;
        }
        return true;
    }

    static boolean checkColumnSame(int column, int start, int height) {
        for (int i = start; i < start + L; i++) {
            if (i < 0 || i >= N) return false;
            if (map[i][column] != height) return false;
            if (installed[i][column]) return false;
            installed[i][column] = true;
        }
        return true;
    }
}