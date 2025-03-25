import java.io.*;
import java.util.*;

class Fish implements Comparable<Fish> {
    int dist;
    int r, c;

    Fish(int dist, int r, int c) {
        this.dist = dist;
        this.r = r;
        this.c = c;
    }

    public int compareTo(Fish o) {
        if (dist == o.dist) {
            if (r == o.r) return c - o.c;
            return r - o.r;
        }
        return dist - o.dist;
    }
}

class Main {
    static int N;
    static int[][] map, visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int sharkSize = 2; // 아기 상어의 크기
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];

        int r = 0, c = 0; // 아기 상어의 위치

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 0) continue; // 빈 칸인 경우
                if (n == 9) { // 아기 상어인 경우
                    r = i;
                    c = j;
                } else { // 물고기인 경우
                    map[i][j] = n;
                }
            }
        }

        bfs(r, c);
        System.out.println(result);
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = 1;
        
        int eaten = 0; // 아기 상어가 먹은 물고기 수
        PriorityQueue<Fish> eatableFishes = new PriorityQueue<>();

        while (true) {
            // 현재 크기의 아기 상어가 갈 수 있는 곳 모두 탐색
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int cr = cur[0];
                int cc = cur[1];

                for (int i = 0; i < 4; i++) {
                    int nr = cr + dr[i];
                    int nc = cc + dc[i];
                    
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                    if (visited[nr][nc] != 0) continue;
                    
                    int fishSize = map[nr][nc];
                    if (fishSize <= sharkSize) { // 지나갈 수 있는 경우
                        q.add(new int[]{nr, nc});
                        visited[nr][nc] = visited[cr][cc] + 1;

                        // 먹을 수 있는 물고기인 경우
                        if (fishSize > 0 && fishSize < sharkSize) {
                            int dist = visited[nr][nc] - 1;
                            eatableFishes.add(new Fish(dist, nr, nc));
                        }
                    }
                }
            }

            // 탐색 결과 먹을 수 있는 물고기가 없는 경우
            if (eatableFishes.isEmpty()) return;

            Fish f = eatableFishes.poll(); // 가장 가까운 물고기
            result += f.dist; // 가장 가까운 물고기로 이동
            eaten++; // 먹은 개수 증가
            map[f.r][f.c] = 0; // 먹은 자리는 빈 칸으로 변경

            // 자신의 크기와 같은 수의 물고기를 먹은 경우
            if (eaten == sharkSize) {
                sharkSize++; // 크기 1 증가
                eaten = 0; // 먹은 개수 초기화
            }

            // 물고기를 먹은 위치로 초기화
            eatableFishes.clear();
            visited = new int[N][N];
            q.add(new int[]{f.r, f.c});
            visited[f.r][f.c] = 1;
        }
    }
}