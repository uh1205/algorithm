import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {

    int node;
    int weight;

    public Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

class Main {

    static int N, M, X, max = 0;
    static List<List<Edge>> graph;
    static int[] distance, time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 도로 수
        X = Integer.parseInt(st.nextToken()); // 파티하는 마을

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        distance = new int[N + 1];
        time = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
        }


        // i번 마을에서 X번 마을로 가는 최소 거리 구하기
        for (int i = 1; i <= N; i++) {
            if (i == X) time[X] = 0;
            else time[i] = calc(i, X);
        }

        // X번 마을에서 i번 마을로 가는 최소 거리 더하기
        for (int i = 1; i <= N; i++) {
            if (i != X) time[i] += calc(X, i);
        }

        for (int t : time) {
            max = Math.max(max, t);
        }

        System.out.println(max);
    }

    // from 마을에서 to 마을로 가는 최소 거리 구하기
    static int calc(int from, int to) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(from, 0));
        distance[from] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int cn = edge.node;
            int cw = edge.weight;

            if (cw > distance[cn]) continue;

            for (Edge e : graph.get(cn)) {
                int nn = e.node;
                int nw = cw + e.weight;
                if (distance[nn] > nw) {
                    distance[nn] = nw;
                    pq.add(new Edge(nn, nw));
                }
            }
        }

        return distance[to];
    }
}