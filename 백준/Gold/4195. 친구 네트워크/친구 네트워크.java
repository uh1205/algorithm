import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] size;
    static Map<String, Integer> nameToId;
    static int idCounter;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());
            parent = new int[2 * F];
            size = new int[2 * F];
            nameToId = new HashMap<>();
            idCounter = 0;

            for (int i = 0; i < 2 * F; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();

                int id1 = getId(name1);
                int id2 = getId(name2);

                sb.append(union(id1, id2)).append('\n');
            }
        }

        System.out.print(sb);
    }

    static int getId(String name) {
        if (!nameToId.containsKey(name)) {
            nameToId.put(name, idCounter++);
        }
        return nameToId.get(name);
    }

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static int union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }

        return size[find(x)];
    }
}