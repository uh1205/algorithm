import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1 = br.readLine();
        String s2 = br.readLine();

        int n1 = s1.length();
        int n2 = s2.length();

        // dp[i][j] : s1의 i번째, s2의 j번째 문자에서 끝나는 최장 공통 부분 문자열 길이
        int[][] dp = new int[n1 + 1][n2 + 1];
        int maxLen = 0;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                // 두 문자가 일치하면 대각선 왼쪽 위 값 + 1
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
                // 일치하지 않으면 0 (연속성이 깨짐)
                else {
                    dp[i][j] = 0;
                }
            }
        }

        System.out.println(maxLen);
    }
}
