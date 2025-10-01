import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] white = new int[N + 1][M + 1];
        int[][] black = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                if (isEdgeBoard(i, j)) {
                    if (line.charAt(j - 1) == 'B') white[i][j] = 1;
                    else black[i][j] = 1;
                } else {
                    if (line.charAt(j - 1) == 'B') black[i][j] = 1;
                    else white[i][j] = 1;
                }
            }
        }

        int[][] whiteSum = new int[N + 1][M + 1];
        int[][] blackSum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                whiteSum[i][j] = whiteSum[i - 1][j] + whiteSum[i][j - 1] - whiteSum[i - 1][j - 1] + white[i][j];
                blackSum[i][j] = blackSum[i - 1][j] + blackSum[i][j - 1] - blackSum[i - 1][j - 1] + black[i][j];
            }
        }

        int minPaint = Integer.MAX_VALUE;

        for (int i = K; i <= N; i++) {
            for (int j = K; j <= M; j++) {
                int ws = whiteSum[i][j] - whiteSum[i - K][j] - whiteSum[i][j - K] + whiteSum[i - K][j - K];
                int bs = blackSum[i][j] - blackSum[i - K][j] - blackSum[i][j - K] + blackSum[i - K][j - K];
                minPaint = Math.min(minPaint, Math.min(ws, bs));
            }
        }

        System.out.println(minPaint);
    }

    static boolean isEdgeBoard(int i, int j) {
        return (i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0);
    }
}