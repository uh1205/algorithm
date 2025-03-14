import java.io.*;
import java.util.*;

public class Main {

    static int N, K, L;
    static int[][] map;
    static int[][] drr;
    static StringTokenizer st;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        K = Integer.parseInt(br.readLine()); // 사과 개수
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 2; // 맵에 사과(2) 저장
        }

        L = Integer.parseInt(br.readLine()); // 뱀 방향 전환 횟수
        drr = new int[L][2];
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int C = Objects.equals(st.nextToken(), "L") ? 1 : 2; // 왼쪽은 1, 오른쪽은 2
            drr[i] = new int[]{X, C};
        }

        // 게임 시작
        int sec = 0;
        int cr = 0, cc = 0; // 뱀 머리의 현재 위치
        int i = 0, d = 0; // 뱀의 방향 (d가 0: 우, 1: 하, 2:좌, 3: 상)
        Queue<int[]> q = new LinkedList<>();

        while (true) {
            // 현재 위치 큐에 저장
            q.add(new int[]{cr, cc});
            map[cr][cc] = 1;

            // 다음 위치 계산
            if (sec == drr[i][0]) { // 방향 전환하는 경우
                if (drr[i][1] == 1) d = (d == 0) ? 3 : d - 1; // 왼쪽
                else d = (d == 3) ? 0 : d + 1; // 오른쪽
                if (i + 1 < L) i++;
            }
            int nr = cr + dr[d];
            int nc = cc + dc[d];

            sec++;
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
            if (map[nr][nc] == 1) break;
            if (map[nr][nc] == 0) {
                int[] tail = q.poll();
                int tr = tail[0];
                int tc = tail[1];
                map[tr][tc] = 0;
            }

            cr = nr;
            cc = nc;
        }

        System.out.println(sec);
    }
}