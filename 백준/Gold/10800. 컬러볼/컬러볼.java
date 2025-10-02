import java.io.*;
import java.util.*;

public class Main {
    static class Ball {
        int idx;   // 입력 순서
        int color; // 색
        int size;  // 크기
        Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, c, s);
        }

        // 크기 기준 오름차순 정렬
        Arrays.sort(balls, (a, b) -> a.size - b.size);

        int[] answer = new int[N];
        int[] colorSum = new int[N + 1]; // 색별 누적합
        int totalSum = 0;                // 전체 누적합

        int j = 0; // 이미 처리된 "더 작은 공들"의 끝 위치
        for (int i = 0; i < N; i++) {
            Ball cur = balls[i];

            // 현재 공보다 작은 공들을 모두 누적합에 반영
            while (balls[j].size < cur.size) {
                totalSum += balls[j].size;
                colorSum[balls[j].color] += balls[j].size;
                j++;
                if (j == N) break;
            }

            // 현재 공의 정답 계산
            answer[cur.idx] = totalSum - colorSum[cur.color];
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.print(sb);
    }
}