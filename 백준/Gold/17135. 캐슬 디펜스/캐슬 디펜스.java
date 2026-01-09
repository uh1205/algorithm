import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D;
    static int[][] board;
    static int enemyCount = 0;
    static int maxKill = 0;

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

        deployArchers(0, 0, new int[3]);

        System.out.println(maxKill);
    }

    static void deployArchers(int start, int count, int[] positions) {
        if (count == 3) {
            maxKill = Math.max(maxKill, simulate(positions));
            return;
        }
        for (int i = start; i < M; i++) {
            positions[count] = i;
            deployArchers(i + 1, count + 1, positions);
        }
    }

    static int simulate(int[] archers) {
        int killCount = 0;
        int[][] board = copyBoard();

        for (int turn = 0; turn < N; turn++) {
            List<int[]> targets = getTargets(archers, board);

            for (int[] target : targets) {
                int r = target[0];
                int c = target[1];
                if (board[r][c] == 1) {
                    board[r][c] = 0;
                    killCount++;
                }
            }

            moveEnemies(board);
        }

        return killCount;
    }

    static List<int[]> getTargets(int[] archers, int[][] board) {
        List<int[]> targets = new ArrayList<>();

        // 각 궁수마다 공격할 적 찾기
        for (int archerCol : archers) {
            int targetR = -1;
            int targetC = -1;
            int minDist = D + 1;

            // 거리 가까운 순, 같다면 c가 작은 순 (왼쪽)
            for (int c = 0; c < M; c++) {
                for (int r = N - 1; r >= 0; r--) {
                    if (board[r][c] == 0) {
                        continue;
                    }
                    int dist = Math.abs(N - r) + Math.abs(archerCol - c);
                    if (dist <= D && dist < minDist) {
                        minDist = dist;
                        targetR = r;
                        targetC = c;
                    }
                }
            }

            if (targetR != -1) {
                targets.add(new int[]{targetR, targetC});
            }
        }
        
        return targets;
    }

    static void moveEnemies(int[][] board) {
        for (int i = N - 1; i > 0; i--) {
            board[i] = board[i - 1];
        }
        board[0] = new int[M];
    }

    static int[][] copyBoard() {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
//            temp[i] = Arrays.copyOf(board[i], M);
            temp[i] = board[i].clone();
        }
        return temp;
    }
}
