class Solution {
    long maxLeaf = 1; // 아무 분배도 하지 않았을 때, 루트의 자식인 초기 리프 노드 1개
    long splitLimit;

    public long solution(int dist_limit, int split_limit) {
        this.splitLimit = split_limit;
        
        // 초기 상태: 누적분배도 P=1, 현재층 노드수 N=1, 남은dist R=dist_limit, 현재리프수=1
        dfs(1, 1, dist_limit, 1);
        
        return maxLeaf;
    }

    private void dfs(long P, long N, long R, long leafCount) {
        // 최대 리프 노드 수 갱신
        if (leafCount > maxLeaf) {
            maxLeaf = leafCount;
        }

        // 분배 노드를 놓을 기회를 모두 소진했다면 종료
        if (R == 0) return;

        // [가지치기] 현재 층의 노드 수가 남은 dist 횟수보다 많거나 같다면,
        // 더 깊이 탐색할 필요 없이 현재 층에서 남은 R을 모두 최선으로 소모하고 종료합니다.
        if (N >= R) {
            if (P * 3 <= splitLimit) {
                // 3분할이 허용된다면 남은 R을 모두 3분할에 사용 (1번 분할당 +2 이익)
                long newLeafCount = leafCount + R * 2;
                if (newLeafCount > maxLeaf) maxLeaf = newLeafCount;
            } else if (P * 2 <= splitLimit) {
                // 3분할은 안되지만 2분할이 허용된다면 모두 2분할에 사용 (1번 분할당 +1 이익)
                long newLeafCount = leafCount + R * 1;
                if (newLeafCount > maxLeaf) maxLeaf = newLeafCount;
            }
            return;
        }

        // 1. 현재 층을 2분할로 진행하는 경우
        if (P * 2 <= splitLimit) {
            long take = Math.min(N, R); // 사용할 분배 노드 개수
            dfs(P * 2, take * 2, R - take, leafCount + take * 1);
        }

        // 2. 현재 층을 3분할로 진행하는 경우
        if (P * 3 <= splitLimit) {
            long take = Math.min(N, R);
            dfs(P * 3, take * 3, R - take, leafCount + take * 2);
        }
    }
}