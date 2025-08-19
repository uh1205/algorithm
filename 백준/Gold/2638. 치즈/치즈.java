import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (true) {
            visited = new boolean[N][M];
            bfsAir(); // 외부 공기 체크

            List<int[]> toMelt = new ArrayList<>();

            // 녹을 치즈 후보 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        int airCnt = 0;
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                                if (visited[nx][ny]) airCnt++; // 외부 공기 접촉
                            }
                        }
                        if (airCnt >= 2) {
                            toMelt.add(new int[]{i, j});
                        }
                    }
                }
            }

            if (toMelt.isEmpty()) break; // 더 이상 녹을 치즈 없음 → 종료

            for (int[] pos : toMelt) {
                map[pos[0]][pos[1]] = 0; // 치즈 녹이기
            }

            time++;
        }

        System.out.println(time);
    }

    // BFS로 외부 공기 탐색
    static void bfsAir() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!visited[nx][ny] && map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}