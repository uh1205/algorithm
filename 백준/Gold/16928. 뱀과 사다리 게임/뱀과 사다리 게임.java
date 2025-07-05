import java.io.*;
import java.util.*;

public class Main {
    static int[] move = new int[101]; // 각 칸의 이동 정보 (사다리/뱀)
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 사다리 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            move[from] = to;
        }

        // 뱀 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            move[from] = to;
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 0}); // 시작 칸, 이동 횟수
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int now = current[0];
            int count = current[1];

            if (now == 100) return count;

            for (int dice = 1; dice <= 6; dice++) {
                int next = now + dice;
                
                if (next > 100 || visited[next]) continue;

                // 사다리나 뱀을 타야 하는 경우
                if (move[next] != 0) {
                    next = move[next];
                }

                if (!visited[next]) {
                    visited[next] = true;
                    q.add(new int[]{next, count + 1});
                }
            }
        }
        
        return -1; // 도달 못할 경우 (문제에서 도달 보장함)
    }
}