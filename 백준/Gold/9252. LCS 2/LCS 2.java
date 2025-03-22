import java.io.*;

public class Main {

    static char[] A, B;
    static int lenA, lenB;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        lenA = A.length;
        lenB = B.length;
        dp = new int[lenA + 1][lenB + 1];

        // LCS 길이 구하기 (DP)
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // LCS 문자열 찾기 (역추적)
        StringBuilder sb = new StringBuilder();
        int i = lenA, j = lenB;
        while (i > 0 && j > 0) {
            if (A[i - 1] == B[j - 1]) { // 공통 문자면 LCS에 추가
                sb.append(A[i - 1]);
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) { // 위쪽 값이 크다면 위로 이동
                i--;
            } else { // 왼쪽 값이 크다면 왼쪽으로 이동
                j--;
            }
        }
        
        System.out.println(dp[lenA][lenB]);
        if (sb.length() > 0) System.out.println(sb.reverse()); // 거꾸로 추가했으므로 뒤집어서 출력
    }
}