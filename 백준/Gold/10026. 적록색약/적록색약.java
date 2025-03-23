import java.io.*;

public class Main {
    static int N;
    static char[][] grid, colorBlindGrid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        grid = new char[N][N];
        colorBlindGrid = new char[N][N];

        // 입력 받으면서 일반 그리드 & 적록색약 그리드 생성
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = line.charAt(j);
                // 적록색약 처리 (R과 G를 동일하게 취급)
                colorBlindGrid[i][j] = (line.charAt(j) == 'G') ? 'R' : line.charAt(j);
            }
        }

        // 일반인의 구역 개수 구하기
        visited = new boolean[N][N];
        int normalCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, grid[i][j], grid);
                    normalCount++;
                }
            }
        }

        // 적록색약의 구역 개수 구하기
        visited = new boolean[N][N];
        int colorBlindCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, colorBlindGrid[i][j], colorBlindGrid);
                    colorBlindCount++;
                }
            }
        }

        // 결과 출력
        System.out.println(normalCount + " " + colorBlindCount);
    }

    // DFS 탐색 함수
    static void dfs(int x, int y, char color, char[][] map) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 체크 및 방문 여부 확인
            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (!visited[nx][ny] && map[nx][ny] == color) {
                    dfs(nx, ny, color, map);
                }
            }
        }
    }
}