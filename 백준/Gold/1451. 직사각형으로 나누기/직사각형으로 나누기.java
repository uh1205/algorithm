import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][M + 1];
        sum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = line.charAt(j - 1) - '0';
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + arr[i][j];
            }
        }

        long ans = 0;

        // 가로 3
        for (int i = 1; i <= N - 2; i++) {
            for (int j = i + 1; j <= N - 1; j++) {
                int s1 = getSum(1, 1, i, M);
                int s2 = getSum(i + 1, 1, j, M);
                int s3 = getSum(j + 1, 1, N, M);
                ans = Math.max(ans, (long) s1 * s2 * s3);
            }
        }

        // 세로 3
        for (int i = 1; i <= M - 2; i++) {
            for (int j = i + 1; j <= M - 1; j++) {
                int s1 = getSum(1, 1, N, i);
                int s2 = getSum(1, i + 1, N, j);
                int s3 = getSum(1, j + 1, N, M);
                ans = Math.max(ans, (long) s1 * s2 * s3);
            }
        }

        // 가로 1, 세로 2
        for (int i = 1; i <= N - 1; i++) {
            for (int j = 1; j <= M - 1; j++) {
                // 상단 1
                int s1 = getSum(1, 1, i, M);
                int s2 = getSum(i + 1, 1, N, j);
                int s3 = getSum(i + 1, j + 1, N, M);
                ans = Math.max(ans, (long) s1 * s2 * s3);

                // 하단 1
                s1 = getSum(i + 1, 1, N, M);
                s2 = getSum(1, 1, i, j);
                s3 = getSum(1, j + 1, i, M);
                ans = Math.max(ans, (long) s1 * s2 * s3);
            }
        }

        // 세로 1, 가로 2
        for (int i = 1; i <= M - 1; i++) {
            for (int j = 1; j <= N - 1; j++) {
                // 좌측 1
                int s1 = getSum(1, 1, N, i);
                int s2 = getSum(1, i + 1, j, M);
                int s3 = getSum(j + 1, i + 1, N, M);
                ans = Math.max(ans, (long) s1 * s2 * s3);

                // 우측 1
                s1 = getSum(1, i + 1, N, M);
                s2 = getSum(1, 1, j, i);
                s3 = getSum(j + 1, 1, N, i);
                ans = Math.max(ans, (long) s1 * s2 * s3);
            }
        }

        System.out.println(ans);
    }

    private static int getSum(int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r1 - 1][c2] - sum[r2][c1 - 1] + sum[r1 - 1][c1 - 1];
    }
}
