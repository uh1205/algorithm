import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int maxSum = 0;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 좌표에서 DFS 탐색 수행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;

                // 예외 모양 처리 (ㅗ, ㅜ, ㅏ, ㅓ)
                checkExtraShape(i, j);
            }
        }

        System.out.println(maxSum);
    }

    // DFS 탐색 (4칸까지 이동)
    public static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, depth + 1, sum + board[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    // ㅗ, ㅜ, ㅏ, ㅓ 블록 확인 (DFS로 만들 수 없는 모양)
    public static void checkExtraShape(int x, int y) {
        if (x >= 1 && y >= 1 && y + 1 < M) { // ㅗ
            int sum = board[x][y] + board[x - 1][y] + board[x][y - 1] + board[x][y + 1];
            maxSum = Math.max(maxSum, sum);
        }
        if (x + 1 < N && y >= 1 && y + 1 < M) { // ㅜ
            int sum = board[x][y] + board[x + 1][y] + board[x][y - 1] + board[x][y + 1];
            maxSum = Math.max(maxSum, sum);
        }
        if (x >= 1 && x + 1 < N && y + 1 < M) { // ㅏ
            int sum = board[x][y] + board[x - 1][y] + board[x + 1][y] + board[x][y + 1];
            maxSum = Math.max(maxSum, sum);
        }
        if (x >= 1 && x + 1 < N && y >= 1) { // ㅓ
            int sum = board[x][y] + board[x - 1][y] + board[x + 1][y] + board[x][y - 1];
            maxSum = Math.max(maxSum, sum);
        }
    }
}