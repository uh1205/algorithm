import java.io.*;
import java.util.*;

public class Main {
    static final String goal = "123456780";
    static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder start = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                start.append(st.nextToken());
            }
        }

        System.out.println(bfs(start.toString()));
    }

    static int bfs(String start) {
        Queue<String> q = new LinkedList<>();
        Map<String, Integer> dist = new HashMap<>();

        q.offer(start);
        dist.put(start, 0);

        while (!q.isEmpty()) {
            String cur = q.poll();
            int d = dist.get(cur);

            if (cur.equals(goal)) return d;

            int zero = cur.indexOf('0');
            int x = zero / 3;
            int y = zero % 3;

            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;

                int swapIdx = nx * 3 + ny;

                String next = swap(cur, zero, swapIdx);
                if (!dist.containsKey(next)) {
                    dist.put(next, d + 1);
                    q.offer(next);
                }
            }
        }

        return -1; // 목표 상태 도달 불가
    }

    static String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return new String(arr);
    }
}