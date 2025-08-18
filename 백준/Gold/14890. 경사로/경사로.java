import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        
        for (int i = 0; i < N; i++) {
            if (isPassableRoad(extractRow(i))) answer++;
            if (isPassableRoad(extractCol(i))) answer++;
        }

        System.out.println(answer);
    }

    // 특정 행 추출
    static int[] extractRow(int r) {
        return Arrays.copyOf(map[r], N);
    }

    // 특정 열 추출
    static int[] extractCol(int c) {
        int[] col = new int[N];
        for (int r = 0; r < N; r++) {
            col[r] = map[r][c];
        }
        return col;
    }

    // 지나갈 수 있는 길인지 확인
    static boolean isPassableRoad(int[] road) {
        boolean[] installed = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int diff = road[i] - road[i + 1];

            if (Math.abs(diff) > 1) return false;

            if (diff == 1) { // 내리막
                if (!canInstall(road, installed, i + 1, road[i + 1])) {
                    return false;
                }
            } else if (diff == -1) { // 오르막
                if (!canInstall(road, installed, i - L + 1, road[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    // 경사로 설치 가능 여부 확인
    static boolean canInstall(int[] road, boolean[] installed, int start, int height) {
        for (int i = start; i < start + L; i++) {
            if (i < 0 || i >= N) return false;
            if (road[i] != height || installed[i]) return false;
            installed[i] = true;
        }
        return true;
    }
}
