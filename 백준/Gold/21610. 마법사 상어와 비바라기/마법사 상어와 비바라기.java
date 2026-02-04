import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] isCloud;
    static List<Node> clouds = new ArrayList<>();

    // 8방향: 1부터 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 구름 생성
        clouds.add(new Node(N - 1, 0));
        clouds.add(new Node(N - 1, 1));
        clouds.add(new Node(N - 2, 0));
        clouds.add(new Node(N - 2, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            simulate(d, s);
        }

        System.out.println(getTotalWater());
    }

    static void simulate(int d, int s) {
        isCloud = new boolean[N][N];

        // 1. 구름 이동 및 2. 비 내리기
        for (Node cloud : clouds) {
            // 연결된 격자 처리: (현재 + (방향*거리)%N + N) % N
            cloud.r = (cloud.r + dx[d] * (s % N) + N) % N;
            cloud.c = (cloud.c + dy[d] * (s % N) + N) % N;
            map[cloud.r][cloud.c]++;
        }

        // 3. 구름이 있었던 위치 표시 (나중에 새 구름 생성 시 제외)
        for (Node cloud : clouds) {
            isCloud[cloud.r][cloud.c] = true;
        }

        // 4. 물복사버그 마법 (대각선 체크)
        for (Node cloud : clouds) {
            int count = 0;
            for (int i = 2; i <= 8; i += 2) { // 대각선 방향: 2, 4, 6, 8
                int nr = cloud.r + dx[i];
                int nc = cloud.c + dy[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] > 0) {
                    count++;
                }
            }
            map[cloud.r][cloud.c] += count;
        }

        // 5. 구름 리스트 초기화 및 새로운 구름 생성
        clouds.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2 && !isCloud[i][j]) {
                    map[i][j] -= 2;
                    clouds.add(new Node(i, j));
                }
            }
        }
    }

    static int getTotalWater() {
        int sum = 0;
        for (int[] row : map) {
            for (int val : row) {
                sum += val;
            }
        }
        return sum;
    }

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
