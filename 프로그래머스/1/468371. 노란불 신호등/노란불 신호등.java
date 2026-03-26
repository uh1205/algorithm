class Solution {
    public int solution(int[][] signals) {
        int n = signals.length;
        
        long lcm = 1;
        for (int i = 0; i < n; i++) {
            int g = signals[i][0];
            int y = signals[i][1];
            int r = signals[i][2];
            int cycle = g + y + r;
            lcm = getLcm(lcm, (long) cycle);
        }
        
        for (int t = 1; t <= lcm; t++) {
            boolean allY = true;
            
            for (int i = 0; i < n; i++) {
                int g = signals[i][0];
                int y = signals[i][1];
                int r = signals[i][2];
                int cycle = g + y + r;
                int time = t % cycle;
                
                if (time <= g || time > g + y) {
                    allY = false;
                    break;
                }
            }
            
            if (allY) return t;
        }
        
        return -1;
    }
    
    long getLcm(long a, long b) {
        return (a * b) / getGcd(a, b);
    }
    
    long getGcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}