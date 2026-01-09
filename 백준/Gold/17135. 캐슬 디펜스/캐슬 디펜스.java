import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D;
    static int[][] board;
    static int enemyCount = 0;
    static int maxKill = 0;
    static int[] archer = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    enemyCount++;
                }
            }
        }

        dfs(0, 0);

        System.out.println(maxKill);
    }

    static void dfs(int start, int depth) {
        // 궁수 배치 완료한 경우
        if (depth == 3) {
            maxKill = Math.max(maxKill, simulate());
            return;
        }
        for (int i = start; i < M; i++) {
            archer[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }

    static int simulate() {
        int killCount = 0;
        int leftEnemy = enemyCount;
        int[][] board = copyBoard();

        while (leftEnemy > 0) {
            // 각 궁수가 공격할 적의 위치
            List<int[]> attack = new ArrayList<>();
            for (int c : archer) {
                chooseToAttack(board, c).ifPresent(attack::add);
            }

            // 공격
            for (int[] a : attack) {
                int r = a[0];
                int c = a[1];
                if (board[r][c] == 1) {
                    board[r][c] = 0;
                    killCount++;
                    leftEnemy--;
                }
            }

            // 적 이동
            for (int val : board[N - 1]) {
                if (val == 1) {
                    leftEnemy--;
                }
            }
            move(board);
        }

        return killCount;
    }

    static int[][] copyBoard() {
        int[][] copied = new int[N][M];
        for (int i = 0; i < N; i++) {
            copied[i] = Arrays.copyOf(board[i], M);
        }
        return copied;
    }

    static Optional<int[]> chooseToAttack(int[][] board, int archerCol) {
        // 가장 가까운 적 찾기
        List<int[]> nearest = new ArrayList<>();
        int minD = D + 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                int distance = Math.abs(N - i) + Math.abs(archerCol - j);
                if (distance > D) {
                    continue;
                }
                if (distance == minD) {
                    nearest.add(new int[]{i, j});
                }
                if (distance < minD) {
                    minD = distance;
                    nearest.clear();
                    nearest.add(new int[]{i, j});
                }
            }
        }

        // 가장 가까운 적이 여러 명일 경우 가장 왼쪽에 있는 적 선택
        if (nearest.size() > 1) {
            nearest.sort(Comparator.comparingInt(a -> a[1]));
        }

        if (nearest.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(nearest.get(0));
    }

    static void move(int[][] board) {
        for (int i = N - 1; i > 0; i--) {
            board[i] = board[i - 1];
        }
        board[0] = new int[M];
    }
}
