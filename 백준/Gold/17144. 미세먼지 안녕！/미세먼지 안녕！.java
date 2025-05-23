import java.io.*;
import java.util.*;

public class Main {

    static int R, C, T;
    static int[][] map, temp;
    static int airCleanerTop = -1;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1 && airCleanerTop == -1) {
                    airCleanerTop = i;
                }
            }
        }

        while (T-- > 0) {
            spreadDust();
            operateAirCleaner();
        }

        System.out.println(getTotalDust());
    }

    static void spreadDust() {
        temp = new int[R][C];

        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (map[x][y] > 0) {
                    int spreadAmount = map[x][y] / 5;
                    int count = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != -1) {
                            temp[nx][ny] += spreadAmount;
                            count++;
                        }
                    }

                    temp[x][y] += map[x][y] - (spreadAmount * count);
                } else if (map[x][y] == -1) {
                    temp[x][y] = -1;
                }
            }
        }

        map = temp;
    }

    static void operateAirCleaner() {
        // 위쪽 (반시계방향)
        for (int i = airCleanerTop - 1; i > 0; i--) map[i][0] = map[i - 1][0];
        for (int i = 0; i < C - 1; i++) map[0][i] = map[0][i + 1];
        for (int i = 0; i < airCleanerTop; i++) map[i][C - 1] = map[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--) map[airCleanerTop][i] = map[airCleanerTop][i - 1];
        map[airCleanerTop][1] = 0;

        // 아래쪽 (시계방향)
        int bottom = airCleanerTop + 1;

        for (int i = bottom + 1; i < R - 1; i++) map[i][0] = map[i + 1][0];
        for (int i = 0; i < C - 1; i++) map[R - 1][i] = map[R - 1][i + 1];
        for (int i = R - 1; i > bottom; i--) map[i][C - 1] = map[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--) map[bottom][i] = map[bottom][i - 1];
        map[bottom][1] = 0;
    }

    static int getTotalDust() {
        int sum = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) sum += map[i][j];
            }
        }

        return sum;
    }
}