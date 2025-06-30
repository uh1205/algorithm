import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int[][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
       
        arr = new int[N + 1];
        dp = new int[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        preprocess();

        int M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(dp[S][E]).append('\n');
        }

        System.out.print(sb);
    }

    static void preprocess() {
        // 길이 1
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        // 길이 2
        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i + 1]) dp[i][i + 1] = 1;
        }

        // 길이 3 이상
        for (int len = 3; len <= N; len++) {
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;
                if (arr[i] == arr[j] && dp[i + 1][j - 1] == 1) {
                    dp[i][j] = 1;
                }
            }
        }
    }
}