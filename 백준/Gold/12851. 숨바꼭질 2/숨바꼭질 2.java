import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100_000;
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 처음 위치
        K = Integer.parseInt(st.nextToken()); // 목표 위치

        int[] answer = bfs();
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    static int[] bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX + 1];

        q.add(new int[]{N, 0});
        visited[N] = true;

        int minTime = Integer.MAX_VALUE;
        int count = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int t = cur[1];
            visited[x] = true;

            if (t > minTime) continue;

            if (x == K) {
                minTime = t;
                count++;
                continue;
            }

            int nx;

            // x-1
            nx = x - 1;
            if (nx >= 0 && !visited[nx]) {
                q.add(new int[]{nx, t + 1});
            }

            // x+1
            nx = x + 1;
            if (nx <= MAX && !visited[nx]) {
                q.add(new int[]{nx, t + 1});
            }

            // x*2
            nx = x * 2;
            if (nx <= MAX && !visited[nx]) {
                q.add(new int[]{nx, t + 1});
            }
        }

        return new int[]{minTime, count};
    }
}