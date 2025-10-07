import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> nameToIdx = new HashMap<>();
        int N = friends.length;
        
        for (int i = 0; i < N; i++) {
            nameToIdx.put(friends[i], i);
        }
        
        int[][] gift = new int[N][N]; // 주고받은 선물 수
        int[] point = new int[N]; // 선물 지수
        
        for (String g : gifts) {
            StringTokenizer st = new StringTokenizer(g);
            int from = nameToIdx.get(st.nextToken());
            int to = nameToIdx.get(st.nextToken());
            gift[from][to]++;
            point[from]++;
            point[to]--;
        }
        
        int[] present = new int[N]; // 다음 달에 받을 선물 수
        
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (gift[i][j] > gift[j][i]) { // i가 더 많이 준 경우
                    present[i]++;
                } else if (gift[i][j] < gift[j][i]) { // j가 더 많이 준 경우
                   present[j]++;
                } else { // 똑같이 주고받은 경우 -> 선물 지수 비교
                    if (point[i] > point[j]) {
                        present[i]++;
                    } else if (point[i] < point[j]) {
                        present[j]++;
                    }
                }
            }
        }
        
        // 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수 구하기
        int answer = 0;
        for (int p : present) {
            answer = Math.max(answer, p);
        }
        return answer;
    }
}