import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 100_001; // 0 ≤ 위치 ≤ 100,000 이므로

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        int[] time = new int[MAX];
        Arrays.fill(time, -1); // 방문하지 않은 곳은 -1

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(N);
        time[N] = 0;

        while (!dq.isEmpty()) {
            int now = dq.poll();

            if (now == K) break; // 도착

            int next;

            // 순간이동 (0초)
            next = now * 2;
            if (next < MAX && time[next] == -1) {
                time[next] = time[now];
                dq.addFirst(next); // 0초이므로 앞에 추가
            }

            // -1 이동 (1초)
            next = now - 1;
            if (next >= 0 && time[next] == -1) {
                time[next] = time[now] + 1;
                dq.addLast(next); // 1초이므로 뒤에 추가
            }

            // +1 이동 (1초)
            next = now + 1;
            if (next < MAX && time[next] == -1) {
                time[next] = time[now] + 1;
                dq.addLast(next); // 1초이므로 뒤에 추가
            }
        }

        System.out.println(time[K]);
    }
}