import java.io.*;
import java.util.*;

public class Main {
    static Node[] tree;
    static StringBuilder pre = new StringBuilder();
    static StringBuilder in = new StringBuilder();
    static StringBuilder post = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new Node[26];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char node = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            tree[node - 'A'].left = left;
            tree[node - 'A'].right = right;
        }

        preorder('A');
        inorder('A');
        postorder('A');

        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
    }

    static void preorder(char node) {
        if (node == '.') return;
        pre.append(node);
        preorder(tree[node - 'A'].left);
        preorder(tree[node - 'A'].right);
    }

    static void inorder(char node) {
        if (node == '.') return;
        inorder(tree[node - 'A'].left);
        in.append(node);
        inorder(tree[node - 'A'].right);
    }

    static void postorder(char node) {
        if (node == '.') return;
        postorder(tree[node - 'A'].left);
        postorder(tree[node - 'A'].right);
        post.append(node);
    }

    static class Node {
        char left;
        char right;

        Node() {
            this.left = '.';
            this.right = '.';
        }
    }
}