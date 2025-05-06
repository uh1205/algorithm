import java.io.*;
import java.util.*;

public class Main {
    static int N, root, deletedNode, leaf = 0;
    static List<List<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken()); // i번 노드의 부모
            if (parent == -1) root = i;
            else tree.get(parent).add(i);
        }

        deletedNode = Integer.parseInt(br.readLine());

        dfs(root);
        System.out.println(leaf);
    }

    static boolean dfs(int node) {
        boolean hasDeletedNode = false;

        if (node == deletedNode) return true;

        // 리프 노드인 경우
        if (tree.get(node).isEmpty()) {
            leaf++;
            return false;
        }

        for (int child : tree.get(node)) {
            hasDeletedNode = dfs(child);
        }

        if (hasDeletedNode && tree.get(node).size() == 1) leaf++;
        return false;
    }
}