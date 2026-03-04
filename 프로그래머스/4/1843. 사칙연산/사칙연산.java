import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int n = arr.length / 2 + 1; // 숫자 개수
        
        int[][] maxDp = new int[n][n];
        int[][] minDp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            // 전체 초기화
            Arrays.fill(maxDp[i], Integer.MIN_VALUE);
            Arrays.fill(minDp[i], Integer.MAX_VALUE);
            
            // 길이 1 구간
            int num = Integer.valueOf(arr[i * 2]);
            maxDp[i][i] = minDp[i][i] = num;
        }
        
        // 길이 2 ~ n 구간
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) { // 0 1 2 3 len=2
                int j = i + len - 1;
                for (int k = i; k < j; k++) { // k번째 숫자 바로 뒤 연산자가 대상임
                    String op = arr[k * 2 + 1];
                    
                    if (op.equals("+")) {
                        maxDp[i][j] = Math.max(maxDp[i][j], 
                                               maxDp[i][k] + maxDp[k + 1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], 
                                               minDp[i][k] + minDp[k + 1][j]);
                    } else {
                        maxDp[i][j] = Math.max(maxDp[i][j], 
                                               maxDp[i][k] - minDp[k + 1][j]);
                        minDp[i][j] = Math.min(minDp[i][j], 
                                               minDp[i][k] - maxDp[k + 1][j]);
                    }
                }
            }
        }
        
        return maxDp[0][n - 1];
    }
}