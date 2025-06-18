import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                int n = Integer.parseInt(st.nextToken());

                if (cmd == 'I') {
                    map.put(n, map.getOrDefault(n, 0) + 1); // 있으면 n + 1, 없으면 0 + 1
                } else if (cmd == 'D') {
                    if (map.isEmpty()) continue;

                    // n이 1이면 최댓값, -1이면 최솟값 제거
                    int key = (n == 1) ? map.lastKey() : map.firstKey();

                    // 남은 개수가 1개면 remove, 아니면 -1
                    if (map.get(key) == 1) {
                        map.remove(key);
                    } else {
                        map.put(key, map.get(key) - 1);
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(map.lastKey()).append(' ').append(map.firstKey()).append('\n');
            }
        }

        System.out.print(sb);
    }
}