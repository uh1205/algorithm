import java.io.*;
import java.util.*;

public class Main {
    static class State {
        int rx, ry, bx, by, depth;

        State(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.depth = depth;
        }
    }

    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            board[i] = line.toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (board[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        System.out.println(bfs(rx, ry, bx, by));
    }

    static int bfs(int rx, int ry, int bx, int by) {
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.depth >= 10) return -1;

            for (int i = 0; i < 4; i++) {
                int[] red = move(cur.rx, cur.ry, dx[i], dy[i]);
                int[] blue = move(cur.bx, cur.by, dx[i], dy[i]);

                int nrx = red[0], nry = red[1], rMove = red[2];
                int nbx = blue[0], nby = blue[1], bMove = blue[2];

                if (board[nbx][nby] == 'O') continue; // 파란 구슬 빠짐 → 실패
                if (board[nrx][nry] == 'O') return cur.depth + 1; // 빨간 구슬 성공

                // 겹쳤을 경우 → 이동 수가 더 큰 구슬을 한 칸 뒤로
                if (nrx == nbx && nry == nby) {
                    if (rMove > bMove) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.add(new State(nrx, nry, nbx, nby, cur.depth + 1));
                }
            }
        }

        return -1;
    }

    static int[] move(int x, int y, int dx, int dy) {
        int moveCount = 0;
        while (board[x + dx][y + dy] != '#' && board[x][y] != 'O') {
            x += dx;
            y += dy;
            moveCount++;
        }
        return new int[]{x, y, moveCount};
    }
}