class Solution {
    
    // 이동 우선순위: d(아래), l(왼쪽), r(오른쪽), u(위)
    private static final char[] MOVES = {'d', 'l', 'r', 'u'};
    private static final int[] DX = {1, 0, 0, -1}; // d: +1, l: 0, r: 0, u: -1
    private static final int[] DY = {0, -1, 1, 0}; // d: 0, l: -1, r: +1, u: 0

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        // 1. 최소 이동 거리 (맨해튼 거리) 계산
        int min_dist = Math.abs(x - r) + Math.abs(y - c);
        
        // 2. 이동 가능성 초기 검사
        // a) k가 최소 거리보다 작은 경우
        if (k < min_dist) {
            return "impossible";
        }
        
        // b) 남은 이동 횟수 (k - min_dist)가 홀수인 경우
        // 남은 거리는 왕복 이동(2의 배수)으로 채워야 하므로 홀수이면 불가능
        if ((k - min_dist) % 2 != 0) {
            return "impossible";
        }
        
        // 3. 사전 순 최소 경로 탐색 (그리디)
        StringBuilder path = new StringBuilder();
        int current_x = x;
        int current_y = y;
        
        for (int i = 0; i < k; i++) {
            boolean foundNextMove = false;
            int k_rem = k - (i + 1); // 남은 이동 횟수
            
            for (int j = 0; j < 4; j++) {
                int next_x = current_x + DX[j];
                int next_y = current_y + DY[j];
                char move = MOVES[j];
                
                // 1. 격자 경계 검사
                if (next_x < 1 || next_x > n || next_y < 1 || next_y > m) {
                    continue;
                }
                
                // 2. 남은 이동 횟수 내에 목표 도달 가능성 검사
                int dist_to_target = Math.abs(next_x - r) + Math.abs(next_y - c);
                
                // a) 남은 최소 거리보다 남은 이동 횟수가 적은 경우 (도달 불가능)
                if (dist_to_target > k_rem) {
                    continue;
                }
                
                // b) 남은 거리와 남은 이동 횟수의 차이가 홀수인 경우 (짝수 개의 왕복 이동으로 채울 수 없음)
                if ((k_rem - dist_to_target) % 2 != 0) {
                    continue;
                }
                
                // 조건을 만족하는 가장 우선순위가 높은 이동 선택
                current_x = next_x;
                current_y = next_y;
                path.append(move);
                foundNextMove = true;
                break; // 사전 순으로 가장 빠른 이동을 찾았으므로 다음 단계로
            }
            
            // 이론적으로 초기 검사를 통과했다면 발생하지 않아야 하지만, 
            // 혹시라도 경로가 막혀 더 이상 이동할 수 없는 경우가 생기면 종료
            if (!foundNextMove) {
                // 하지만 이 문제는 초기 검사를 통과하면 항상 경로가 존재하므로,
                // 이 분기는 최종 도착 여부 확인에 의존
                break; 
            }
        }
        
        // 4. 최종 위치 확인: k번 이동 후 (r, c)에 정확히 도달했는지 확인
        if (current_x == r && current_y == c && path.length() == k) {
            return path.toString();
        } else {
            return "impossible";
        }
    }
}