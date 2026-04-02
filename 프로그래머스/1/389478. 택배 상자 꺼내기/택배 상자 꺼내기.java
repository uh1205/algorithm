class Solution {
    public int solution(int n, int w, int num) {
        int targetR = (num - 1) / w;
        int targetC = getCol(num, w, targetR);
        
        int lastR = (n - 1) / w;
        int lastC = getCol(n, w, lastR);
        
        int ans = (lastR - 1) - targetR + 1;
        
        if (targetC == lastC) return ans + 1;
        
        boolean right = false;
        if (lastR % 2 == 0) {
            right = true;
        }
        
        if (targetC < lastC && right) return ans + 1;
        if (targetC > lastC && !right) return ans + 1;
        
        return ans;
    }
    
    int getCol(int num, int w, int row) {
        int pos = (num - 1) % w;
        
        if (row % 2 == 0) {
            return pos;
        } else {
            return (w - 1) - pos;
        }
    }
}