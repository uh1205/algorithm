import java.io.*;
import java.util.*;

public class Main {
    static int N, M, unripeCount = 0;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        int[][] queue = new int[N * M][2]; // ✅ 배열을 이용한 큐
        int start = 0, end = 0; // 큐의 시작과 끝 인덱스

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    queue[end][0] = i;
                    queue[end++][1] = j; // ✅ 큐에 삽입
                } else if (map[i][j] == 0) {
                    unripeCount++; // ✅ 익지 않은 토마토 개수 저장
                }
            }
        }

        // 모든 토마토가 이미 익은 상태이면 0 출력 후 종료
        if (unripeCount == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs(queue, start, end));
    }

    static int bfs(int[][] queue, int start, int end) {
        int days = 0;

        while (start < end) {
            int currentEnd = end; // 현재 큐의 끝 지점 저장
            for (int i = start; i < currentEnd; i++) { // ✅ 직접 큐 범위를 지정하여 탐색
                int cr = queue[i][0], cc = queue[i][1];

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d], nc = cc + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] != 0) continue;

                    map[nr][nc] = 1; // ✅ 방문 체크 & 익음 처리
                    unripeCount--;  // ✅ 익지 않은 토마토 개수 감소
                    queue[end][0] = nr;
                    queue[end++][1] = nc; // ✅ 큐에 삽입
                }
            }
            start = currentEnd; // ✅ 다음 단계에서 탐색할 영역 조정
            if (start < end) days++; // ✅ 큐가 비어있지 않으면 날짜 증가
        }

        return (unripeCount == 0) ? days : -1; // ✅ 더 빠르게 검사 가능
    }
}