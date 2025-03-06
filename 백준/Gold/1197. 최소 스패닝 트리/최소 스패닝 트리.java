import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {

    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        return this.weight - node.weight;
    }
}

class Main {

    static int V, E;
    static boolean[] visited;
    static PriorityQueue<Node> pq;
    static ArrayList<ArrayList<Node>> nodes;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        visited = new boolean[V + 1];
        pq = new PriorityQueue<>();

        nodes = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            nodes.add(new ArrayList<>());
        }

        // 간선 정보 입력 (정점 연결리스트)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodes.get(start).add(new Node(end, weight));
            nodes.get(end).add(new Node(start, weight));
        }

        pq.add(new Node(1, 0));

        int sum = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int end = node.end;
            int weight = node.weight;

            // 이미 방문한 정점이라면 탐색 X
            if (visited[end]) continue;
            visited[end] = true;
            sum += weight;

            // 연결되어 있는 방문하지 않은 정점 큐에 추가
            for (Node next : nodes.get(end)) {
                if (!visited[next.end]) {
                    pq.add(next);
                }
            }
        }

        System.out.println(sum);
    }
}