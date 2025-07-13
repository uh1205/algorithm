import java.io.*;

public class Main {
    static int[] left = new int[26];
    static int[] right = new int[26];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int root = input[0].charAt(0) - 'A';
            char l = input[1].charAt(0);
            char r = input[2].charAt(0);

            left[root] = (l == '.') ? -1 : l - 'A';
            right[root] = (r == '.') ? -1 : r - 'A';
        }

        preorder(0);
        sb.append('\n');

        inorder(0);
        sb.append('\n');

        postorder(0);

        System.out.print(sb);
    }

    static void preorder(int node) {
        if (node == -1) return;
        sb.append((char) (node + 'A'));
        preorder(left[node]);
        preorder(right[node]);
    }

    static void inorder(int node) {
        if (node == -1) return;
        inorder(left[node]);
        sb.append((char) (node + 'A'));
        inorder(right[node]);
    }

    static void postorder(int node) {
        if (node == -1) return;
        postorder(left[node]);
        postorder(right[node]);
        sb.append((char) (node + 'A'));
    }
}