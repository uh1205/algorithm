import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // R번 회전 수행
        for (int i = 0; i < R; i++) {
            rotate();
        }

        // 결과 출력 (StringBuilder 권장)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void rotate() {
        int layers = Math.min(N, M) / 2;

        for (int i = 0; i < layers; i++) {
            int temp = map[i][i]; // 시작점 저장 (왼쪽 위)

            // 1. 위쪽 변 (왼쪽으로 이동)
            for (int j = i; j < M - 1 - i; j++) {
                map[i][j] = map[i][j + 1];
            }

            // 2. 오른쪽 변 (위쪽으로 이동)
            for (int j = i; j < N - 1 - i; j++) {
                map[j][M - 1 - i] = map[j + 1][M - 1 - i];
            }

            // 3. 아래쪽 변 (오른쪽으로 이동)
            for (int j = M - 1 - i; j > i; j--) {
                map[N - 1 - i][j] = map[N - 1 - i][j - 1];
            }

            // 4. 왼쪽 변 (아래쪽으로 이동)
            for (int j = N - 1 - i; j > i + 1; j--) {
                map[j][i] = map[j - 1][i];
            }

            map[i + 1][i] = temp; // 저장해둔 값 배치
        }
    }
}