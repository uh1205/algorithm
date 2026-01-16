import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] seat = new int[N][N];
        int[][] likes = new int[N * N + 1][4];

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                likes[student][j] = Integer.parseInt(st.nextToken());
            }

            // 1. 빈 자리 중에서 주위에 친구가 가장 많은 자리
            List<int[]> candidate = new ArrayList<>();
            int maxLike = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (seat[r][c] == 0) { // 비어있는 칸
                        int like = 0;
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                                continue;
                            }
                            for (int friend : likes[student]) {
                                if (seat[nr][nc] == friend) {
                                    like++;
                                    break;
                                }
                            }
                        }
                        if (like < maxLike) {
                            continue;
                        }
                        if (like > maxLike) {
                            maxLike = like;
                            candidate.clear();
                        }
                        candidate.add(new int[]{r, c});
                    }
                }
            }

            if (candidate.size() == 1) {
                int[] pos = candidate.get(0);
                seat[pos[0]][pos[1]] = student;
                continue;
            }

            // 2. 같다면 주위에 빈 자리가 가장 많은 자리
            List<int[]> candidate2 = new ArrayList<>();
            int maxEmpty = 0;
            for (int[] pos : candidate) {
                int r = pos[0];
                int c = pos[1];
                int empty = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }
                    if (seat[nr][nc] == 0) {
                        empty++;
                    }
                }
                if (empty < maxEmpty) {
                    continue;
                }
                if (empty > maxEmpty) {
                    maxEmpty = empty;
                    candidate2.clear();
                }
                candidate2.add(new int[]{r, c});
            }

            if (candidate2.size() == 1) {
                int[] pos = candidate2.get(0);
                seat[pos[0]][pos[1]] = student;
                continue;
            }

            // 3. 같다면 가장 위, 가장 왼쪽
            int[] pos = candidate2.get(0);
            seat[pos[0]][pos[1]] = student;
        }

        // 학생의 만족도 (인접한 칸에 앉은 좋아하는 학생의 수)
        int ans = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }
                    for (int friend : likes[seat[r][c]]) {
                        if (seat[nr][nc] == friend) {
                            count++;
                            break;
                        }
                    }
                }
                ans += (int) Math.pow(10, count - 1);
            }
        }

        System.out.println(ans);
    }
}
