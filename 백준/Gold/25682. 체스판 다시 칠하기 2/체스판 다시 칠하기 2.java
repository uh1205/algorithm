import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            board[i] = line.toCharArray();
        }

        // prefixW : 왼쪽 위가 'W' 패턴에서 불일치(1) 누적합, 1-based 인덱스
        // prefixB : 왼쪽 위가 'B' 패턴에서 불일치(1) 누적합
        int[][] prefixW = new int[N + 1][M + 1];
        int[][] prefixB = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 실제 보드 좌표는 board[i-1][j-1]
                char ch = board[i - 1][j - 1];

                // expected for W-start pattern: if (i+j) % 2 == 0 -> 'W', else 'B'
                char expectW = ((i + j) % 2 == 0) ? 'W' : 'B';
                char expectB = (expectW == 'W') ? 'B' : 'W';

                int diffW = (ch == expectW) ? 0 : 1;
                int diffB = (ch == expectB) ? 0 : 1;

                prefixW[i][j] = prefixW[i - 1][j] + prefixW[i][j - 1] - prefixW[i - 1][j - 1] + diffW;
                prefixB[i][j] = prefixB[i - 1][j] + prefixB[i][j - 1] - prefixB[i - 1][j - 1] + diffB;
            }
        }

        int best = Integer.MAX_VALUE;

        // 모든 KxK의 좌상단 (r,c) : 1..N-K+1, 1..M-K+1 (prefix는 1-based)
        for (int r = 1; r <= N - K + 1; r++) {
            int r2 = r + K - 1;
            for (int c = 1; c <= M - K + 1; c++) {
                int c2 = c + K - 1;

                int sumW = prefixW[r2][c2] - prefixW[r - 1][c2] - prefixW[r2][c - 1] + prefixW[r - 1][c - 1];
                int sumB = prefixB[r2][c2] - prefixB[r - 1][c2] - prefixB[r2][c - 1] + prefixB[r - 1][c - 1];

                int need = Math.min(sumW, sumB);
                if (need < best) best = need;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(best).append('\n');
        System.out.print(sb.toString());
    }
}