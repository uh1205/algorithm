import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int maxBlock = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, 0);
        System.out.println(maxBlock);
    }

    // 깊이 우선 탐색
    private static void dfs(int[][] board, int depth) {
        if (depth == 5) {
            updateMax(board);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int[][] newBoard = move(board, dir);
            dfs(newBoard, depth + 1);
        }
    }

    // 현재 보드에서 가장 큰 블록을 갱신
    private static void updateMax(int[][] board) {
        for (int[] row : board) {
            for (int val : row) {
                maxBlock = Math.max(maxBlock, val);
            }
        }
    }

    // 이동 시뮬레이션
    private static int[][] move(int[][] board, int dir) {
        int[][] newBoard = new int[N][N];

        // 깊은 복사
        for (int i = 0; i < N; i++) {
            newBoard[i] = board[i].clone();
        }

        for (int i = 0; i < N; i++) {
            int[] line = new int[N];
            int idx = 0;

            switch (dir) {
                case 0: // 위쪽
                    for (int j = 0; j < N; j++) {
                        if (newBoard[j][i] != 0) line[idx++] = newBoard[j][i];
                    }
                    line = merge(line);
                    for (int j = 0; j < N; j++) {
                        newBoard[j][i] = line[j];
                    }
                    break;

                case 1: // 아래쪽
                    for (int j = N - 1; j >= 0; j--) {
                        if (newBoard[j][i] != 0) line[idx++] = newBoard[j][i];
                    }
                    line = merge(line);
                    for (int j = N - 1, k = 0; j >= 0; j--, k++) {
                        newBoard[j][i] = line[k];
                    }
                    break;

                case 2: // 왼쪽
                    for (int j = 0; j < N; j++) {
                        if (newBoard[i][j] != 0) line[idx++] = newBoard[i][j];
                    }
                    line = merge(line);
                    for (int j = 0; j < N; j++) {
                        newBoard[i][j] = line[j];
                    }
                    break;

                case 3: // 오른쪽
                    for (int j = N - 1; j >= 0; j--) {
                        if (newBoard[i][j] != 0) line[idx++] = newBoard[i][j];
                    }
                    line = merge(line);
                    for (int j = N - 1, k = 0; j >= 0; j--, k++) {
                        newBoard[i][j] = line[k];
                    }
                    break;
            }
        }

        return newBoard;
    }

    // 블록 합치기
    private static int[] merge(int[] line) {
        LinkedList<Integer> result = new LinkedList<>();
        int i = 0;
        while (i < line.length) {
            if (line[i] == 0) {
                i++;
                continue;
            }

            int current = line[i];
            if (i + 1 < line.length && line[i] == line[i + 1]) {
                result.add(current * 2);
                i += 2;
            } else {
                result.add(current);
                i++;
            }
        }

        while (result.size() < N) {
            result.add(0);
        }

        int[] merged = new int[N];
        for (int j = 0; j < N; j++) {
            merged[j] = result.get(j);
        }

        return merged;
    }
}