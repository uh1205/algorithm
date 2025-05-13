import java.io.*;
import java.util.*;

public class Main {

    static String[] commands = {"D", "S", "L", "R"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            System.out.println(bfs(A, B));
        }
    }

    static String bfs(int start, int target) {
        boolean[] visited = new boolean[10000];
        Queue<Node> queue = new LinkedList<>();

        queue.offer(new Node(start, ""));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.value == target) {
                return current.commands;
            }

            // D
            int D = (current.value * 2) % 10000;
            if (!visited[D]) {
                visited[D] = true;
                queue.offer(new Node(D, current.commands + "D"));
            }

            // S
            int S = current.value == 0 ? 9999 : current.value - 1;
            if (!visited[S]) {
                visited[S] = true;
                queue.offer(new Node(S, current.commands + "S"));
            }

            // L
            int L = (current.value % 1000) * 10 + current.value / 1000;
            if (!visited[L]) {
                visited[L] = true;
                queue.offer(new Node(L, current.commands + "L"));
            }

            // R
            int R = (current.value % 10) * 1000 + current.value / 10;
            if (!visited[R]) {
                visited[R] = true;
                queue.offer(new Node(R, current.commands + "R"));
            }
        }

        return "";
    }

    static class Node {
        int value;
        String commands;

        Node(int value, String commands) {
            this.value = value;
            this.commands = commands;
        }
    }
}