import java.io.*;
import java.util.*;

public class Main {
    static int N, sharkSize = 2, eatCount = 0, totalTime = 0;
    static int[][] map;
    static int sx, sy; // 아기 상어 위치

    // 방향 (상, 좌, 우, 하) - 거리 같을 경우 위 -> 왼쪽 우선순위 유지
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sx = i;
                    sy = j;
                    map[i][j] = 0; // 상어 위치 초기화
                }
            }
        }

        while (true) {
            Fish target = findNearestFish();
            if (target == null) break; // 먹을 수 있는 물고기가 없으면 종료

            // 이동 및 물고기 섭취
            totalTime += target.dist;
            sx = target.x;
            sy = target.y;
            map[sx][sy] = 0;

            eatCount++;
            if (eatCount == sharkSize) {
                sharkSize++; // 상어 크기 증가
                eatCount = 0;
            }
        }

        System.out.println(totalTime);
    }

    // BFS 탐색 최적화 (거리 -> 위쪽 -> 왼쪽 우선순위 적용)
    public static Fish findNearestFish() {
        Queue<Fish> queue = new LinkedList<>();
        int[][] distance = new int[N][N];
        queue.add(new Fish(sx, sy, 0));
        distance[sx][sy] = 1; // 방문 처리 (0이 아니라 1부터 시작)

        Fish bestFish = null;
        int minDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Fish cur = queue.poll();

            // 현재 거리가 최소 거리보다 크면 탐색 종료 (불필요한 탐색 방지)
            if (cur.dist > minDist) break;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || distance[nx][ny] > 0) continue;
                if (map[nx][ny] > sharkSize) continue; // 상어보다 큰 물고기는 지나갈 수 없음

                distance[nx][ny] = cur.dist + 1;
                queue.add(new Fish(nx, ny, cur.dist + 1));

                // 먹을 수 있는 물고기 발견
                if (map[nx][ny] > 0 && map[nx][ny] < sharkSize) {
                    if (cur.dist + 1 < minDist) {
                        minDist = cur.dist + 1;
                        bestFish = new Fish(nx, ny, minDist);
                    } else if (cur.dist + 1 == minDist) {
                        if (nx < bestFish.x || (nx == bestFish.x && ny < bestFish.y)) {
                            bestFish = new Fish(nx, ny, minDist);
                        }
                    }
                }
            }
        }

        return bestFish;
    }

    // 물고기 위치 및 거리 저장 클래스
    static class Fish {
        int x, y, dist;
        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}