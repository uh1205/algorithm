import java.io.*;

public class Main {
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        board = new char[N][N];
        draw(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(board[i]).append('\n');
        }
        System.out.print(sb);
    }

    // r, c : 시작 좌표
    // size : 현재 정사각형 크기
    // blank : 공백만 채워야 하는지 여부
    static void draw(int r, int c, int size, boolean blank) {
        if (blank) {
            // 공백 채우기
            for (int i = r; i < r + size; i++) {
                for (int j = c; j < c + size; j++) {
                    board[i][j] = ' ';
                }
            }
            return;
        }

        if (size == 1) {
            board[r][c] = '*';
            return;
        }

        int newSize = size / 3;
        int cnt = 0;
        for (int i = r; i < r + size; i += newSize) {
            for (int j = c; j < c + size; j += newSize) {
                cnt++;
                if (cnt == 5) { // 가운데 블록
                    draw(i, j, newSize, true);
                } else {
                    draw(i, j, newSize, false);
                }
            }
        }
    }
}