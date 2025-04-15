import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        int[] time = new int[MAX];
        Arrays.fill(time, -1); // 방문하지 않은 곳은 -1

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(N);
        time[N] = 0;

        while (!deque.isEmpty()) {
            int now = deque.poll();

            if (now == K) {
                System.out.println(time[now]);
                return;
            }

            // 순간이동: 0초
            int next = now * 2;
            if (next < MAX && time[next] == -1) {
                time[next] = time[now];
                deque.addFirst(next); // 0초이므로 앞에 추가
            }

            // 걷기: 1초
            for (int dir : new int[]{now - 1, now + 1}) {
                if (dir >= 0 && dir < MAX && time[dir] == -1) {
                    time[dir] = time[now] + 1;
                    deque.addLast(dir); // 1초이므로 뒤에 추가
                }
            }
        }
    }
}