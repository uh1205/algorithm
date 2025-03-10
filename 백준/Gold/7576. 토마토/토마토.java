import java.io.*;
import java.util.*;

public class Main {
    
    static int N, M;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int cnt = 0; // 익지 않은 토마토 개수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    q.add(new int[]{i, j});
                } else if (map[i][j] == 0) {
                    cnt++; // 익지 않은 토마토 개수 카운트
                }
            }
        }

        // 모든 토마토가 이미 익은 상태이면 0 출력 후 종료
        if (cnt == 0) {
            System.out.println(0);
            return;
        }
        
        System.out.println(bfs());
    }

    static int bfs() {
        int days = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] current = q.poll();
                int cr = current[0];
                int cc = current[1];

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                    if (map[nr][nc] == 0) {
                        q.add(new int[]{nr, nc});
                        map[nr][nc] = 1; // 방문 체크 & 익음 처리
                    }
                }
            }
            if (!q.isEmpty()) days++;
        }

        // 익지 않은 토마토가 남아 있는지 확인
        for (int[] row : map) {
            for (int tomato : row) {
                if (tomato == 0) return -1;
            }
        }

        return days;
    }
}