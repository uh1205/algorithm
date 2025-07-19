import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        int totalCheese = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) totalCheese++;
            }
        }

        int time = 0;
        int lastCount = 0;

        while (totalCheese > 0) {
            lastCount = totalCheese;
            visited = new boolean[N][M];
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            visited[0][0] = true;

            List<int[]> toMelt = new ArrayList<>();

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    if (board[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny});
                    } else if (board[nx][ny] == 1) {
                        toMelt.add(new int[]{nx, ny});
                    }
                }
            }

            for (int[] cheese : toMelt) {
                board[cheese[0]][cheese[1]] = 0;
                totalCheese--;
            }

            time++;
        }

        System.out.println(time);
        System.out.println(lastCount);
    }
}