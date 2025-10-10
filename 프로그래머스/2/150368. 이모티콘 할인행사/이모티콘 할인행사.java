import java.util.*;

class Solution {
    static int[] discountPercent = {10, 20, 30, 40};
    static int[] ans = new int[2];
    
    public int[] solution(int[][] users, int[] emoticons) {
        int n = emoticons.length;
        
        dfs(n, 0, new int[n], users, emoticons);
        
        return ans;
    }
    
    void dfs(int n, int idx, int[] discounts, int[][] users, int[] emoticons) {
        if (idx == n) {
            // System.out.println(Arrays.toString(discounts));
            calc(discounts, users, emoticons);
            return;
        }
        for (int p : discountPercent) {
            discounts[idx] = p;
            dfs(n, idx + 1, discounts, users, emoticons); // 재귀 호출
        }
    }
    
    void calc(int[] discounts, int[][] users, int[] emoticons) {
        int totalJoin = 0;
        int totalPrice = 0;
        
        for (int[] user : users) {
            boolean join = false;
            int buy = 0;
            
            for (int i = 0; i < discounts.length; i++) {
                if (discounts[i] >= user[0]) {
                    buy += emoticons[i] - emoticons[i] * discounts[i] / 100;
                }
                if (buy >= user[1]) {
                    join = true;
                    break;
                }
            }
            
            if (join) totalJoin++;
            else totalPrice += buy;
        }
        
        if (totalJoin > ans[0] || (totalJoin == ans[0] && totalPrice > ans[1])) {
            ans[0] = totalJoin;
            ans[1] = totalPrice;
        }
    }
}