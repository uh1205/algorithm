import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] board = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<Integer> directions = new ArrayList<>();
            directions.add(d);

            // 세대별 방향 생성
            for (int gen = 0; gen < g; gen++) {
                for (int idx = directions.size() - 1; idx >= 0; idx--) {
                    directions.add((directions.get(idx) + 1) % 4);
                }
            }

            // 점 찍기
            board[y][x] = true;
            for (int dir : directions) {
                x += dx[dir];
                y += dy[dir];
                board[y][x] = true;
            }
        }

        // 1x1 정사각형 개수 세기
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (board[i][j] && board[i][j+1] && board[i+1][j] && board[i+1][j+1]) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}