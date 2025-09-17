import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        compress(N, 0, 0);

        System.out.println(sb);
    }

    static void compress(int n, int r, int c) {
        if (n == 1) {
            sb.append(map[r][c]);
            return;
        }

        boolean allSame = true;
        char ch = map[r][c];
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (map[i][j] != ch) {
                    allSame = false;
                    break;
                }
            }
        }

        if (allSame) {
            sb.append(ch);
            return;
        }

        sb.append('(');

        // 1. 좌상
        compress(n / 2, r, c);
        // 2. 우상
        compress(n / 2, r, c + n / 2);
        // 3. 좌하
        compress(n / 2, r + n / 2, c);
        // 4. 우하
        compress(n / 2, r + n / 2, c + n / 2);

        sb.append(')');
    }
}