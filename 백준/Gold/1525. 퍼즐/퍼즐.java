import java.io.*;
import java.util.*;

public class Main {
    static final String GOAL = "123456780";
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                sb.append(st.nextToken());
            }
        }

        System.out.println(bfs(sb.toString()));
    }

    static int bfs(String map) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.add(map);
        visited.add(map);

        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            
            while (size-- > 0) {
                String cur = q.poll();

                if (cur.equals(GOAL)) {
                    return depth;
                }

                int zeroIdx = cur.indexOf("0");
                int cr = zeroIdx / 3;
                int cc = zeroIdx % 3;

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if (nr < 0 || nc < 0 || nr >= 3 || nc >= 3) continue;

                    int swapIdx = nr * 3 + nc;
                    String next = swap(cur, zeroIdx, swapIdx);

                    if (!visited.contains(next)) {
                        q.add(next);
                        visited.add(next);
                    }
                }
            }
            
            depth++;
        }

        return -1;
    }

    static String swap(String str, int i, int j) {
        char[] arr = str.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }
}