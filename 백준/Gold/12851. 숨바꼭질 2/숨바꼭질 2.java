import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dist = new int[MAX + 1]; // 최단 시간
        int[] cnt = new int[MAX + 1]; // 방법 수
        
        Arrays.fill(dist, -1);

        // BFS 시작
        Queue<Integer> q = new LinkedList<>();

        // 처음 위치 초기화
        q.add(N); 
        dist[N] = 0;
        cnt[N] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : new int[]{cur - 1, cur + 1, cur * 2}) {
                if (next < 0 || next > MAX) continue;

                if (dist[next] == -1) { // 처음 방문
                    dist[next] = dist[cur] + 1;
                    cnt[next] = cnt[cur];
                    q.add(next);
                } else if (dist[next] == dist[cur] + 1) { // 다른 최단 경로 발견
                    cnt[next] += cnt[cur];
                }
            }
        }

        System.out.println(dist[K]);
        System.out.println(cnt[K]);
    }
}