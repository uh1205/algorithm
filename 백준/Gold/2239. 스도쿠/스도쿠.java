import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static int[] rowMask = new int[9];     // 각 행에 사용된 숫자 비트(1~9 -> bit 1~9)
    static int[] colMask = new int[9];     // 각 열에 사용된 숫자 비트
    static int[][] boxMask = new int[3][3];// 각 3x3 박스에 사용된 숫자 비트
    static List<int[]> blanks = new ArrayList<>();
    static boolean solved = false;
    static StringBuilder out = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력: 9줄, 공백 없이 9자리 숫자
        for (int r = 0; r < 9; r++) {
            String line = br.readLine().trim();
            for (int c = 0; c < 9; c++) {
                int v = line.charAt(c) - '0';
                board[r][c] = v;
                if (v == 0) {
                    blanks.add(new int[]{r, c}); // 행→열 순서로 저장(사전식 보장)
                } else {
                    int bit = 1 << v;
                    rowMask[r] |= bit;
                    colMask[c] |= bit;
                    boxMask[r / 3][c / 3] |= bit;
                }
            }
        }

        dfs(0);

        // 해는 항상 존재(문제 보장). out에 결과가 채워져 있음.
        System.out.print(out);
    }

    // idx번째 빈칸을 채운다(빈칸 순서 고정: 행 0..8, 열 0..8)
    static void dfs(int idx) {
        if (solved) return;
        if (idx == blanks.size()) {
            // 해 완성 → 사전식 최소(변수 순서/값 순서 보장) 첫 해 출력
            printBoard();
            solved = true;
            return;
        }

        int[] pos = blanks.get(idx);
        int r = pos[0], c = pos[1];

        // 사용된 숫자 비트 통합
        int used = rowMask[r] | colMask[c] | boxMask[r / 3][c / 3];

        // 후보: (~used) & 0b1111111110 (bit1~bit9만 유효)
        int candidates = (~used) & 0x3FE;

        // 사전식 최소 해를 위해 값은 1→9 오름차순 시도
        for (int num = 1; num <= 9; num++) {
            int bit = 1 << num;
            if ((candidates & bit) == 0) continue;

            place(r, c, num, bit);
            dfs(idx + 1);
            if (solved) return;
            remove(r, c, num, bit);
        }
    }

    static void place(int r, int c, int num, int bit) {
        board[r][c] = num;
        rowMask[r] |= bit;
        colMask[c] |= bit;
        boxMask[r / 3][c / 3] |= bit;
    }

    static void remove(int r, int c, int num, int bit) {
        board[r][c] = 0;
        rowMask[r] ^= bit;
        colMask[c] ^= bit;
        boxMask[r / 3][c / 3] ^= bit;
    }

    static void printBoard() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) out.append(board[r][c]);
            out.append('\n');
        }
    }
}