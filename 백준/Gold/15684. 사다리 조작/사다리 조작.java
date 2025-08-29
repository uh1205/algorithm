import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static boolean[][] ladder;
    static int answer = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로선 개수
        M = Integer.parseInt(st.nextToken()); // 가로선 개수
        H = Integer.parseInt(st.nextToken()); // 점선 위치의 개수

        if (M == 0) {
            System.out.println(0);
            return;
        }

        ladder = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a번 점선 위치의 b번 세로선과 b+1번 세로선을 연결
            ladder[a][b] = true;
        }

        dfs(0, 1, 1);
        System.out.println(answer > 3 ? -1 : answer);
    }

    static void dfs(int count, int row, int col) {
        // 이미 찾은 최소 개수보다 많아지면 중단
        if (count >= answer) return;

        if (check()) {
            answer = count;
            return;
        }

        // 3개를 추가해도 정답이 아니면 중단
        if (count == 3) return;

        // 다음 행으로 넘어갈 때 열을 다시 1번부터 시작
        for (int r = row; r <= H; r++, col = 1) {
            for (int c = col; c < N; c++) {
                if (ladder[r][c - 1] || ladder[r][c] || ladder[r][c + 1]) continue;

                ladder[r][c] = true;

                // 현재 행의 다음 열부터 탐색 (인접 방지를 위해 c+2)
                dfs(count + 1, r, c + 2); 

                ladder[r][c] = false;
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= N; i++) {
            int cur = i;
            for (int h = 1; h <= H; h++) {
                if (ladder[h][cur]) cur++;
                else if (ladder[h][cur - 1]) cur--;
            }
            if (cur != i) return false;
        }
        return true;
    }
}