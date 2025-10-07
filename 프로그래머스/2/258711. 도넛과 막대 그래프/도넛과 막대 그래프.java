import java.util.*;

/**
 * 도넛, 막대, 8자 모양 그래프 찾기 문제 솔루션
 */
class Solution {
    public int[] solution(int[][] edges) {
        // 각 정점의 [진입 차수, 진출 차수]를 저장할 맵
        Map<Integer, int[]> nodeInfo = new HashMap<>();

        // 1. 모든 간선 정보를 순회하며 각 정점의 진입/진출 차수 계산
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            // 맵에 노드가 없으면 초기화
            nodeInfo.putIfAbsent(from, new int[]{0, 0});
            nodeInfo.putIfAbsent(to, new int[]{0, 0});

            // 진출 차수(from)와 진입 차수(to) 증가
            nodeInfo.get(from)[1]++;
            nodeInfo.get(to)[0]++;
        }

        // 결과를 담을 배열 [생성된 정점 번호, 도넛, 막대, 8자]
        int[] answer = new int[4];
        
        // 2. 생성된 정점 찾기 및 그래프 종류별 개수 계산
        for (Map.Entry<Integer, int[]> entry : nodeInfo.entrySet()) {
            int node = entry.getKey();
            int inDegree = entry.getValue()[0];
            int outDegree = entry.getValue()[1];

            // 생성된 정점의 조건: 진입 차수 0, 진출 차수 2 이상
            if (inDegree == 0 && outDegree >= 2) {
                answer[0] = node;
            }
            // 막대 그래프의 끝점 조건: 진출 차수 0
            else if (outDegree == 0) {
                answer[2]++; // 막대 그래프 수 증가
            }
            // 8자 그래프의 중앙점 조건: 진출 차수 2, 진입 차수 2 이상
            else if (outDegree == 2 && inDegree >= 2) {
                answer[3]++; // 8자 모양 그래프 수 증가
            }
        }
        
        // 3. 도넛 모양 그래프 개수 계산
        // (전체 그래프 수) = (생성된 정점의 진출 차수)
        // (도넛 그래프 수) = (전체 그래프 수) - (막대 그래프 수) - (8자 그래프 수)
        int totalGraphs = nodeInfo.get(answer[0])[1];
        answer[1] = totalGraphs - answer[2] - answer[3];

        return answer;
    }
}