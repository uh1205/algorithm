import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static boolean[][] ladder;
    static int answer = 4; // 3보다 크면 -1 출력 예정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H + 1][N + 1]; // 1-indexed

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }

        dfs(0, 1, 1);

        System.out.println(answer > 3 ? -1 : answer);
    }

    static void dfs(int count, int row, int col) {
        if (count >= answer) return; // 이미 최소값보다 크면 중단
        if (check()) { // 목표 조건 만족 시 최소값 갱신
            answer = count;
            return;
        }
        if (count == 3) return; // 3 초과 불가

        for (int r = row; r <= H; r++, col = 1) {
            for (int c = col; c < N; c++) {
                if (ladder[r][c] || ladder[r][c - 1] || ladder[r][c + 1]) continue;
                ladder[r][c] = true;
                dfs(count + 1, r, c + 2); // 인접 가로선 방지 위해 c+2
                ladder[r][c] = false;
            }
        }
    }

    static boolean check() {
        for (int start = 1; start <= N; start++) {
            int pos = start;
            for (int r = 1; r <= H; r++) {
                if (ladder[r][pos]) pos++;
                else if (pos > 1 && ladder[r][pos - 1]) pos--;
            }
            if (pos != start) return false;
        }
        return true;
    }
}