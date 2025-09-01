import java.io.*;
import java.util.*;

public class Main {
    static int[][] map = new int[9][9];
    static List<int[]> zero = new ArrayList<>();
    static boolean solved = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = line.charAt(j) - '0';
                if (map[i][j] == 0) zero.add(new int[]{i, j});
            }
        }

        dfs(0);
        printMap();
    }

    static void dfs(int depth) {
        if (depth == zero.size()) {
            solved = true;
            return;
        }

        int[] cur = zero.get(depth);
        int r = cur[0];
        int c = cur[1];

        for (int num = 1; num <= 9; num++) {
            if (isValid(r, c, num)) {
                map[r][c] = num;
                dfs(depth + 1);
                if (solved) return;
                map[r][c] = 0;
            }
        }
    }

    static boolean isValid(int r, int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (map[r][i] == num || map[i][c] == num) return false;
        }
        int sr = (r / 3) * 3;
        int sc = (c / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (map[i][j] == num) return false;
            }
        }
        return true;
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

}