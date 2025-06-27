import java.io.*;
import java.util.*;

public class Main {
    static int[] map = new int[101];
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 초기화
        for (int i = 1; i <= 100; i++) map[i] = i;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사다리 수
        int M = Integer.parseInt(st.nextToken()); // 뱀 수

        // 사다리 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x] = y;
        }

        // 뱀 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map[u] = v;
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 0}); // 시작 칸, 주사위 횟수
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int pos = cur[0];
            int cnt = cur[1];

            if (pos == 100) return cnt;

            for (int dice = 1; dice <= 6; dice++) {
                int next = pos + dice;

                if (next > 100) continue;

                next = map[next]; // 사다리 또는 뱀 적용

                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[]{next, cnt + 1});
                }
            }
        }
        return -1; // 문제 조건상 항상 도착 가능하므로 이 경우는 없음
    }
}