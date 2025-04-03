import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static List<CCTV> cctvs = new ArrayList<>();
    static int minBlindSpots = Integer.MAX_VALUE;

    // CCTV 방향 설정 (우, 좌, 하, 상)
    static int[][] directions = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    // CCTV 별 감시 가능한 방향 (회전 포함)
    static int[][][] cctvDirections = {
            {}, // 0번 (사용 X)
            {{0}, {1}, {2}, {3}}, // 1번
            {{0, 2}, {1, 3}}, // 2번
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3번
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, // 4번
            {{0, 1, 2, 3}} // 5번
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 입력 받기 & CCTV 정보 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        // 백트래킹을 사용하여 CCTV 방향 설정 및 탐색
        dfs(0, map);
        System.out.println(minBlindSpots);
    }

    // 백트래킹 DFS로 모든 CCTV 배치 탐색
    static void dfs(int depth, int[][] map) {
        if (depth == cctvs.size()) {
            minBlindSpots = Math.min(minBlindSpots, countBlindSpots(map));
            return;
        }

        CCTV cctv = cctvs.get(depth);
        int type = cctv.type;

        // CCTV 종류에 따른 모든 방향 탐색
        for (int[] directions : cctvDirections[type]) { // 2번일 경우: {0, 2}, {1, 3}
            int[][] copiedMap = copyMap(map);
            for (int dir : directions) {
                markSurveillanceArea(copiedMap, cctv.x, cctv.y, dir);
            }
            dfs(depth + 1, copiedMap);
        }
    }

    // 사각지대 개수 카운트
    static int countBlindSpots(int[][] map) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) count++;
            }
        }
        return count;
    }

    // 감시 영역 표시
    static void markSurveillanceArea(int[][] map, int x, int y, int dir) {
        int nx = x;
        int ny = y;

        while (true) {
            nx += directions[dir][0];
            ny += directions[dir][1];

            // 범위 초과 또는 벽(6) 만나면 중지
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 6) break;

            // CCTV가 아니라면 감시 영역으로 표시
            if (map[nx][ny] == 0) {
                map[nx][ny] = -1; // 감시 표시
            }
        }
    }

    // 2차원 배열 복사
    static int[][] copyMap(int[][] original) {
        int[][] copied = new int[N][M];
        for (int i = 0; i < N; i++) {
            copied[i] = original[i].clone();
        }
        return copied;
    }

    // CCTV 위치 정보를 저장하는 클래스
    static class CCTV {
        int x, y, type;

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}