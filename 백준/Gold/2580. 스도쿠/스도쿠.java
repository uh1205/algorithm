import java.io.*;
import java.util.*;

public class Main {

    static int[][] board = new int[9][9];
    static List<int[]> blanks = new ArrayList<>();
    static boolean finished = false;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                }
            }
        }

        dfs(0);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int idx) {
        if (idx == blanks.size()) {
            finished = true;
            return;
        }

        int[] pos = blanks.get(idx);
        int x = pos[0];
        int y = pos[1];

        for (int num = 1; num <= 9; num++) {
            if (isValid(x, y, num)) {
                board[x][y] = num;
                dfs(idx + 1);
                if (finished) return;
                board[x][y] = 0;
            }
        }
    }

    static boolean isValid(int x, int y, int num) {
        // 행, 열 체크
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == num || board[i][y] == num) {
                return false;
            }
        }

        // 3x3 박스 체크
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;

        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}