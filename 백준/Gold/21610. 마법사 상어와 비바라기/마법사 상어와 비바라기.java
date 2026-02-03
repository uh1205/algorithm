import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] water;
    static boolean[][] cloud;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        water = new int[N + 1][N + 1];
        cloud = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 비바라기 시전
        cloud[N][1] = true;
        cloud[N][2] = true;
        cloud[N - 1][1] = true;
        cloud[N - 1][2] = true;

        // 구름 이동 명령
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            // 1. 모든 구름 이동
            while (s-- > 0) {
                move(d);
            }

            // 2. 각 구름에서 비 내리기
            rain();

            // 3. 모든 구름 사라지기
            // 실제로 지우진 말고 사라졌다고 가정

            // 4. 비 내린 칸에 물복사버그 시전 (대각선 물 바구니 수만큼 물 증가)
            addWater();

            // 5. 물이 2 이상인 칸에 구름 생성 후 물 양 -2 (구름이 사라진 칸이 아니어야 함)
            createCloud();
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ans += water[i][j];
            }
        }

        System.out.println(ans);
    }

    static void move(int d) {
        if (dr[d] == -1) {
            // 위로
            for (int c = 1; c <= N; c++) {
                boolean temp = cloud[1][c];
                for (int r = 1; r < N; r++) {
                    cloud[r][c] = cloud[r + 1][c];
                }
                cloud[N][c] = temp;
            }
        }
        if (dr[d] == 1) {
            // 아래로
            for (int c = 1; c <= N; c++) {
                boolean temp = cloud[N][c];
                for (int r = N; r > 1; r--) {
                    cloud[r][c] = cloud[r - 1][c];
                }
                cloud[1][c] = temp;
            }
        }
        if (dc[d] == -1) {
            // 왼쪽으로
            for (int r = 1; r <= N; r++) {
                boolean temp = cloud[r][1];
                for (int c = 1; c < N; c++) {
                    cloud[r][c] = cloud[r][c + 1];
                }
                cloud[r][N] = temp;
            }
        }
        if (dc[d] == 1) {
            // 오른쪽으로
            for (int r = 1; r <= N; r++) {
                boolean temp = cloud[r][N];
                for (int c = N; c > 1; c--) {
                    cloud[r][c] = cloud[r][c - 1];
                }
                cloud[r][1] = temp;
            }
        }
    }

    static void rain() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (cloud[i][j]) {
                    water[i][j]++;
                }
            }
        }
    }

    static void addWater() {
        int[][] newWater = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (cloud[i][j]) {
                    newWater[i][j] = countWater(i, j);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                water[i][j] += newWater[i][j];
            }
        }
    }

    static int countWater(int r, int c) {
        int count = 0;
        if (r - 1 >= 1 && c - 1 >= 1 && water[r - 1][c - 1] > 0) {
            count++;
        }
        if (r - 1 >= 1 && c + 1 <= N && water[r - 1][c + 1] > 0) {
            count++;
        }
        if (r + 1 <= N && c - 1 >= 1 && water[r + 1][c - 1] > 0) {
            count++;
        }
        if (r + 1 <= N && c + 1 <= N && water[r + 1][c + 1] > 0) {
            count++;
        }
        return count;
    }

    static void createCloud() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 이전에 구름이 없던 지역이어야 함
                if (water[i][j] >= 2 && !cloud[i][j]) {
                    cloud[i][j] = true;
                    water[i][j] -= 2;
                } else if (cloud[i][j]) {
                    cloud[i][j] = false;
                }
            }
        }
    }
}
