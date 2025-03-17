import java.io.*;
import java.util.*;

class State {
    int r, c, key, dist;
    
    State(int r, int c, int key, int dist) {
        this.r = r;
        this.c = c;
        this.key = key;
        this.dist = dist;
    }
}

class Main {
    
    static int N, M;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new char[N][M];
        visited = new boolean[N][M][64]; // 2^6 = 64가지 키 상태

        int r = 0, c = 0;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '0') {
                    r = i;
                    c = j;
                }
            }
        }

        System.out.println(bfs(r, c));
    }

    static int bfs(int r, int c) {
        Queue<State> q = new LinkedList<>();
        q.add(new State(r, c, 0, 0));
        visited[r][c][0] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            // 출구 도착
            if (map[cur.r][cur.c] == '1') return cur.dist;

            // 네 방향 이동
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                int key = cur.key;

                // 범위 체크
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (map[nr][nc] == '#' || visited[nr][nc][key]) continue;

                char next = map[nr][nc];

                // 열쇠 획득 (a~f)
                if ('a' <= next && next <= 'f') {
                    key |= (1 << (next - 'a'));
                }
                // 문 통과 (A~F)
                if ('A' <= next && next <= 'F') {
                    if ((key & (1 << (next - 'A'))) == 0) continue;
                }

                // 이동 가능하면 큐에 추가
                visited[nr][nc][key] = true;
                q.add(new State(nr, nc, key, cur.dist + 1));
            }
        }

        return -1; // 탈출 불가
    }

}