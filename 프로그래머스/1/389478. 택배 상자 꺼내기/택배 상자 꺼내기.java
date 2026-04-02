class Solution {
    public int solution(int n, int w, int num) {
        int h = (int) Math.ceil((double) n / w);
        
        int[][] arr = new int[h][w];
        
        boolean right = true;
        int r = 0, c = 0;
        int x = 0, y = 0;
        
        for (int i = 1; i <= n; i++) {
            arr[r][c] = i;
            if (i == num) {
                x = r;
                y = c;
            }
            if (right) {
                c++;
            } else {
                c--;
            }
            if (c < 0) {
                r++;
                c = 0;
                right = true;
            }
            else if (c >= w) {
                r++;
                c = w - 1;
                right = false;
            }
        }
        
        int ans = 0;
        while (x < h && arr[x][y] > 0) {
            ans++;
            x++;
        }
        
        return ans;
    }
}