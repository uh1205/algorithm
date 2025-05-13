import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 10000;
    static int[] from = new int[MAX];   // 이전 노드
    static char[] how = new char[MAX];  // 어떤 명령어로 왔는지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            sb.append(bfs(A, B)).append("\n");
        }

        System.out.print(sb);
    }

    static String bfs(int start, int target) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        
        boolean[] visited = new boolean[MAX];
        visited[start] = true;

        from[start] = -1;

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == target) break;

            // D
            int next = (now * 2) % 10000;
            if (!visited[next]) {
                visited[next] = true;
                q.add(next);
                from[next] = now;
                how[next] = 'D';
            }

            // S
            next = (now == 0) ? 9999 : now - 1;
            if (!visited[next]) {
                visited[next] = true;
                q.add(next);
                from[next] = now;
                how[next] = 'S';
            }

            // L
            next = (now % 1000) * 10 + now / 1000;
            if (!visited[next]) {
                visited[next] = true;
                q.add(next);
                from[next] = now;
                how[next] = 'L';
            }

            // R
            next = (now % 10) * 1000 + now / 10;
            if (!visited[next]) {
                visited[next] = true;
                q.add(next);
                from[next] = now;
                how[next] = 'R';
            }
        }

        // 명령어 경로 추적
        StringBuilder sb = new StringBuilder();
        int cur = target;
        while (from[cur] != -1) {
            sb.append(how[cur]);
            cur = from[cur];
        }

        return sb.reverse().toString();
    }
}