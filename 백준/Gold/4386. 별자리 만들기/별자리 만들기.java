import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int target;
    double weight;

    Node(int target, double weight) {
        this.target = target;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.weight, o.weight);
    }
}

class Star {
    double x, y;

    Star(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Star[] stars = new Star[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i] = new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        // 인접 리스트 생성 (모든 별이 서로 연결된 완전 그래프)
        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
                adj.get(i).add(new Node(j, dist));
                adj.get(j).add(new Node(i, dist));
            }
        }

        // 프림 알고리즘
        boolean[] visited = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0)); // 0번 별부터 시작

        double ans = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (visited[curr.target]) {
                continue;
            }

            visited[curr.target] = true;
            ans += curr.weight;
            if (++count == n) {
                break;
            }

            for (Node next : adj.get(curr.target)) {
                if (!visited[next.target]) {
                    pq.add(next);
                }
            }
        }

        System.out.printf("%.2f\n", ans);
    }
}
