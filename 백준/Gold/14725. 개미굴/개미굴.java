import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    String root, name;
    int depth;
    PriorityQueue<Node> nodes;

    Node(String root, String name, int depth) {
        this.root = root;
        this.name = name;
        this.depth = depth;
        nodes = new PriorityQueue<>();
    }

    public boolean contains(Node node) {
        return nodes.contains(node);
    }

    public Node get(Node node) {
        for (Node n : nodes) {
            if (n.equals(node)) {
                return n;
            }
        }
        return null;
    }

    public void insert(Node node) {
        nodes.add(node);
    }

    @Override
    public int compareTo(Node o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(root, node.root)
                && Objects.equals(name, node.name)
                && depth == node.depth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, name, depth);
    }
}

class Main {
    static Node root = new Node("root", "root", -1);
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String[] feeds = new String[K];
            for (int j = 0; j < K; j++) {
                feeds[j] = st.nextToken();
            }
            insert(K, feeds);
        }

        PriorityQueue<Node> nodes = root.nodes;
        while (!nodes.isEmpty()) {
            printResult(nodes.poll());
        }
        System.out.println(result);
    }

    private static void printResult(Node node) {
        // 본인 출력
        for (int i = 0; i < node.depth; i++) {
            result.append("--");
        }
        result.append(node.name).append('\n');

        // 자식 출력
        PriorityQueue<Node> nodes = node.nodes;
        while (!nodes.isEmpty()) {
            printResult(nodes.poll());
        }
    }

    static void insert(int k, String[] feeds) {
        Node parent = root;
        for (int i = 0; i < k; i++) {
            Node child = new Node(feeds[0], feeds[i], i);
            if (parent.contains(child)) {
                parent = parent.get(child); // 이미 가지고 있는 child 줘야됨
            } else {
                parent.insert(child);
                parent = child;
            }

        }
    }
}