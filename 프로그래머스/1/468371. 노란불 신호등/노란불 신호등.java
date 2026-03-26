class Solution {
    public int solution(int[][] signals) {
        int n = signals.length;
        int MAX = 3_200_000;
        
        int[] yellow = new int[MAX];
        for (int i = 0; i < n; i++) {
            int cycle = signals[i][0] + signals[i][1] + signals[i][2];
            int start = signals[i][0] + 1;
            int end = start + signals[i][1] - 1;
            while (end < MAX) {
                for (int j = start; j <= end; j++) {
                    yellow[j]++;
                }
                start += cycle;
                end += cycle;
            }
        }
        
        int ans = -1;
        for (int i = 0; i < MAX; i++) {
            if (yellow[i] == n) {
                ans = i;
                break;
            }
        }
        return ans;
    }
}