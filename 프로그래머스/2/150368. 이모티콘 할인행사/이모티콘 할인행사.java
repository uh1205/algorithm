class Solution {
    static int[] answer = new int[2];
    static int[] discountRates = {10, 20, 30, 40};

    public int[] solution(int[][] users, int[] emoticons) {
        // DFS를 통해 모든 할인율 조합을 탐색
        // 첫 번째 이모티콘(인덱스 0)부터 시작, 할인율 조합을 담을 배열 생성
        dfs(0, new int[emoticons.length], users, emoticons);
        return answer;
    }

    // 각 이모티콘의 할인율을 결정하는 재귀 함수
    void dfs(int idx, int[] discounts, int[][] users, int[] emoticons) {
        // 모든 이모티콘의 할인율이 결정된 경우
        if (idx == emoticons.length) {
            calculate(discounts, users, emoticons);
            return;
        }

        // 현재 이모티콘에 모든 할인율을 적용해보기
        for (int rate : discountRates) {
            discounts[idx] = rate;
            dfs(idx + 1, discounts, users, emoticons);
        }
    }

    // 결정된 할인율 조합으로 가입자 수와 판매액을 계산하고, 최적의 결과를 갱신
    void calculate(int[] discounts, int[][] users, int[] emoticons) {
        int currentSubscribers = 0;
        int currentSales = 0;

        // 각 사용자에 대해 결과 시뮬레이션
        for (int[] user : users) {
            int userDiscountRate = user[0];
            int userPriceThreshold = user[1];
            boolean isSubscriber = false;
            int totalPurchase = 0;

            // 사용자의 기준에 따라 이모티콘 구매
            for (int i = 0; i < emoticons.length; i++) {
                if (discounts[i] >= userDiscountRate) {
                    totalPurchase += emoticons[i] * (100 - discounts[i]) / 100;
                }
                // 구매 비용이 기준을 넘으면 플러스 서비스 가입
                if (totalPurchase >= userPriceThreshold) {
                    isSubscriber = true;
                    break;
                }
            }

            if (isSubscriber) currentSubscribers++;
            else currentSales += totalPurchase;
        }

        // 최적의 결과인지 확인하고 갱신
        // 1. 가입자 수가 더 많으면 무조건 갱신
        // 2. 가입자 수가 같으면 판매액이 더 많을 때만 갱신
        if (currentSubscribers > answer[0] ||
                currentSubscribers == answer[0] && currentSales > answer[1]) {
            answer[0] = currentSubscribers;
            answer[1] = currentSales;
        }
    }
}