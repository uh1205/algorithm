import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    static int[] size;
    static Map<String, Integer> map;
    static int idx;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());

            parent = new int[F * 2];
            size = new int[F * 2];
            map = new HashMap<>();
            idx = 0;

            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();

                int id1 = getId(name1);
                int id2 = getId(name2);

                sb.append(union(id1, id2)).append("\n");
            }
        }

        System.out.print(sb);
    }

    static int getId(String name) {
        if (!map.containsKey(name)) {
            map.put(name, idx++);
        }
        return map.get(name);
    }

    static int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    static int union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }

        return size[find(rootA)];
    }
}