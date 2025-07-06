import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        boolean[][] dp = new boolean[N + 1][N + 1]; // dp[i][j] = i ~ j 구간이 팰린드롬?

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 전처리: 길이 1
        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        // 전처리: 길이 2
        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        // 전처리: 길이 3 이상
        for (int len = 3; len <= N; len++) {
            for (int s = 1; s <= N - len + 1; s++) {
                int e = s + len - 1;
                if (arr[s] == arr[e] && dp[s + 1][e - 1]) {
                    dp[s][e] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 질의 시작
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(dp[s][e] ? 1 : 0).append('\n');
        }

        System.out.println(sb);
    }
}