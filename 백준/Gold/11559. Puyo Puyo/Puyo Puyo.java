import java.io.*;
import java.util.*;

public class Main {
    static char[][] board = new char[12][6];
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static boolean isPopped;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int totalChain = 0;

        while (true) {
            isPopped = false;
            boolean[][] visited = new boolean[12][6];

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] != '.' && !visited[i][j]) {
                        bfs(i, j, visited);
                    }
                }
            }

            if (!isPopped) {
                break;
            }

            applyGravity();
            totalChain++;
        }

        System.out.println(totalChain);
    }

    static void bfs(int r, int c, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> list = new ArrayList<>();

        int[] start = {r, c};
        q.add(start);
        list.add(start);
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6 ||
                        visited[nr][nc] ||
                        board[nr][nc] != board[r][c]) {
                    continue;
                }

                int[] next = {nr, nc};
                q.add(next);
                list.add(next);
                visited[nr][nc] = true;
            }
        }

        if (list.size() >= 4) {
            isPopped = true;
            for (int[] pos : list) {
                board[pos[0]][pos[1]] = '.';
            }
        }
    }

    static void applyGravity() {
        for (int i = 10; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] != '.' && board[i + 1][j] == '.') {
                    int h = i + 1;
                    while (h < 11 && board[h + 1][j] == '.') {
                        h++;
                    }
                    board[h][j] = board[i][j];
                    board[i][j] = '.';
                }
            }
        }
    }
}
