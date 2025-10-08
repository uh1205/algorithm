class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long ans = 0;
        int farthest = n - 1; // 가야할 가장 먼 집의 인덱스
        
        while (farthest >= 0) {
            // 배달 및 수거할 상자가 없는 경우
            if (deliveries[farthest] == 0 && pickups[farthest] == 0) {
                farthest--;
                continue;
            }
            
            ans += 2 * (farthest + 1); // 왕복 거리
            
            // 뒤에서부터 배달
            int d = cap;
            for (int i = farthest; i >= 0; i--) { 
                if (deliveries[i] >= d) {
                    deliveries[i] -= d;
                    break;
                }
                d -= deliveries[i];
                deliveries[i] = 0;
            }
            
            // 돌아오면서 수거
            int p = cap;
            for (int i = farthest; i >= 0; i--) { 
                if (pickups[i] >= p) {
                    pickups[i] -= p;
                    break;
                }
                p -= pickups[i];
                pickups[i] = 0;
            }
        }
        
        return ans;
    }
}