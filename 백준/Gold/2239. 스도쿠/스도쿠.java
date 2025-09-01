import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static boolean[][] rowUsed = new boolean[9][10];
    static boolean[][] colUsed = new boolean[9][10];
    static boolean[][] boxUsed = new boolean[9][10];
    static List<int[]> blanks = new ArrayList<>();
    static boolean solved = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                int num = line.charAt(j) - '0';
                board[i][j] = num;
                if (num == 0) {
                    blanks.add(new int[]{i, j});
                } else {
                    rowUsed[i][num] = true;
                    colUsed[j][num] = true;
                    boxUsed[boxIndex(i, j)][num] = true;
                }
            }
        }

        dfs(0);
        printBoard();
    }

    static int boxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }

    static void dfs(int idx) {
        if (idx == blanks.size()) {
            solved = true;
            return;
        }

        int[] pos = blanks.get(idx);
        int r = pos[0];
        int c = pos[1];

        for (int num = 1; num <= 9; num++) {
            if (rowUsed[r][num] || colUsed[c][num] ||
                    boxUsed[boxIndex(r, c)][num]) continue;

            board[r][c] = num;
            rowUsed[r][num] = colUsed[c][num] = boxUsed[boxIndex(r, c)][num] = true;

            dfs(idx + 1);
            if (solved) return;

            board[r][c] = 0;
            rowUsed[r][num] = colUsed[c][num] = boxUsed[boxIndex(r, c)][num] = false;
        }
    }

    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}