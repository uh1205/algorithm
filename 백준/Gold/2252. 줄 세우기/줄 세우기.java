import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[N + 1];
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            inDegree[B]++;
        }

        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(' ');

            for (int next : graph.get(cur)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        System.out.println(sb);
    }
}
