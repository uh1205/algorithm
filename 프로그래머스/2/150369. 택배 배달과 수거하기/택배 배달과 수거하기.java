class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        // 처리해야 할 배달 및 수거량을 누적할 변수
        int deliverCount = 0;
        int pickupCount = 0;

        // 가장 먼 집부터 역순으로 순회
        for (int i = n - 1; i >= 0; i--) {
            // 현재 집에서 처리해야 할 배달/수거량을 누적
            deliverCount += deliveries[i];
            pickupCount += pickups[i];

            // 현재 집에 들러야 하는 경우 (처리할 배달 또는 수거가 있는 경우)
            while (deliverCount > 0 || pickupCount > 0) {
                // 이 집까지 왕복
                answer += (long) (i + 1) * 2;

                // 한 번의 왕복으로 cap만큼의 배달과 수거를 처리함
                deliverCount -= cap;
                pickupCount -= cap;

                // 처리할 배달 또는 수거가 없을 때까지 반복
            }
        }

        return answer;
    }
}