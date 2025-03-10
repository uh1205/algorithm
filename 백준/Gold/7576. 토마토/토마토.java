import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] box;
    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    static int[] dy = {-1, 1, 0, 0};
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        int unripeCount = 0; // 익지 않은 토마토 개수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) queue.offer(new int[]{i, j});
                else if (box[i][j] == 0) unripeCount++; // 익지 않은 토마토 개수 카운트
            }
        }

        // 모든 토마토가 이미 익은 상태이면 0 출력 후 종료
        if (unripeCount == 0) {
            System.out.println(0);
            return;
        }

        // BFS 실행
        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        int days = 0; // ✅ 시작을 0일로 수정

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx >= 0 && ny >= 0 && nx < N && ny < M && box[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny});
                        box[nx][ny] = 1; // 방문 체크 & 익음 처리
                    }
                }
            }

            // ✅ 하루 증가 위치를 루프 끝으로 이동
            if (!queue.isEmpty()) days++;
        }

        // 익지 않은 토마토가 남아 있는지 확인
        for (int[] row : box) {
            for (int tomato : row) {
                if (tomato == 0) return -1;
            }
        }

        return days;
    }
}