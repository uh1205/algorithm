import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] board;
    static boolean[] visited = new boolean[26]; // 알파벳 방문 여부
    static int max = 0;

    // 방향 벡터 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 빠른 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // 시작 알파벳 방문 체크
        visited[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(max);
    }

    static void dfs(int x, int y, int count) {
        max = Math.max(max, count);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 보드 범위 안이고, 아직 방문하지 않은 알파벳이면 이동
            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                int index = board[nx][ny] - 'A';
                if (!visited[index]) {
                    visited[index] = true;
                    dfs(nx, ny, count + 1);
                    visited[index] = false; // 백트래킹
                }
            }
        }
    }
}
