import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N; // 도시의 수
    private static int[][] W; // 비용 행렬
    private static int[][] dp; // 동적 계획법을 위한 메모이제이션 배열
    
    // 모든 도시를 방문했음을 나타내는 비트마스크 (예: N=4일 경우 1111(2) = 15)
    private static int ALL_VISITED; 
    
    // 경로가 없을 때 사용할 매우 큰 값 (Integer.MAX_VALUE는 오버플로우를 일으킬 수 있으므로 적절한 값을 사용)
    private static final int INF = 16 * 1_000_000 + 1; 

    public static void main(String[] args) throws IOException {
        // 입력을 받기 위한 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 테이블과 변수 초기화
        ALL_VISITED = (1 << N) - 1;
        dp = new int[N][1 << N]; // dp[현재도시][방문한도시들]

        // DP 테이블을 -1로 초기화하여 아직 계산되지 않았음을 표시
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 0번 도시에서 출발하여 순회 시작
        // 초기 방문 상태는 0번 도시만 방문했으므로 비트마스크는 1 (00...01)
        int result = tsp(0, 1);

        System.out.println(result);
    }

    /**
     * 외판원 순회 문제를 해결하는 재귀 함수 (Top-down DP)
     * @param current 현재 위치한 도시
     * @param visitedMask 지금까지 방문한 도시들을 나타내는 비트마스크
     * @return 현재 상태에서 남은 도시들을 모두 방문하고 출발지로 돌아가는 데 드는 최소 비용
     */
    private static int tsp(int current, int visitedMask) {
        // 1. 모든 도시를 방문한 경우
        if (visitedMask == ALL_VISITED) {
            // 현재 도시에서 출발 도시(0번)로 돌아갈 수 있는지 확인
            if (W[current][0] == 0) {
                return INF; // 돌아가는 경로가 없으면 무한대 비용 반환
            }
            return W[current][0]; // 돌아가는 비용 반환
        }

        // 2. 이미 계산된 값이 있다면 그 값을 반환 (메모이제이션)
        if (dp[current][visitedMask] != -1) {
            return dp[current][visitedMask];
        }

        // 3. 아직 계산되지 않은 경우, 최소 비용 계산
        // 현재 상태의 최소 비용을 일단 무한대로 초기화
        dp[current][visitedMask] = INF;

        // 다음으로 방문할 도시(next)를 탐색
        for (int next = 0; next < N; next++) {
            // 조건 1: next 도시를 아직 방문하지 않았어야 함
            // (visitedMask & (1 << next)) == 0 으로 확인
            // 조건 2: 현재 도시(current)에서 next 도시로 가는 경로가 있어야 함
            // W[current][next] != 0 으로 확인
            if ((visitedMask & (1 << next)) == 0 && W[current][next] != 0) {
                
                // next 도시를 방문한 것으로 처리하고 재귀 호출
                // visitedMask | (1 << next)는 기존 방문 상태에 next 도시 방문을 추가
                int cost = W[current][next] + tsp(next, visitedMask | (1 << next));
                
                // 기존에 계산된 최소 비용과 새로 계산된 비용을 비교하여 더 작은 값으로 갱신
                dp[current][visitedMask] = Math.min(dp[current][visitedMask], cost);
            }
        }

        return dp[current][visitedMask];
    }
}
