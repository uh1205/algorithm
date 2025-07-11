import java.io.*;
import java.util.*;

public class Main {
    static Node[] tree = new Node[26]; // A~Z 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 노드 초기화
        for (int i = 0; i < 26; i++) {
            tree[i] = new Node();
        }

        // 트리 입력 구성
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            int idx = parent - 'A';
            tree[idx].left = (left == '.') ? -1 : left - 'A';
            tree[idx].right = (right == '.') ? -1 : right - 'A';
        }

        StringBuilder pre = new StringBuilder();
        StringBuilder in = new StringBuilder();
        StringBuilder post = new StringBuilder();

        preorder(0, pre); // 루트는 항상 A (인덱스 0)
        inorder(0, in);
        postorder(0, post);

        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
    }

    static void preorder(int node, StringBuilder sb) {
        if (node == -1) return;
        sb.append((char) (node + 'A'));
        preorder(tree[node].left, sb);
        preorder(tree[node].right, sb);
    }

    static void inorder(int node, StringBuilder sb) {
        if (node == -1) return;
        inorder(tree[node].left, sb);
        sb.append((char) (node + 'A'));
        inorder(tree[node].right, sb);
    }

    static void postorder(int node, StringBuilder sb) {
        if (node == -1) return;
        postorder(tree[node].left, sb);
        postorder(tree[node].right, sb);
        sb.append((char) (node + 'A'));
    }

    static class Node {
        int left = -1;
        int right = -1;
    }
}