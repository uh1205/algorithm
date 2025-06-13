import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String command = br.readLine();

            if (command.startsWith("push")) {
                int x = Integer.parseInt(command.split(" ")[1]);
                stack.push(x);
            } else if (command.equals("pop")) {
                sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
            } else if (command.equals("size")) {
                sb.append(stack.size()).append("\n");
            } else if (command.equals("empty")) {
                sb.append(stack.isEmpty() ? 1 : 0).append("\n");
            } else if (command.equals("top")) {
                sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
            }
        }

        System.out.print(sb);
    }
}