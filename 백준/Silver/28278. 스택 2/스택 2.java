import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case 1:
                    int X = Integer.parseInt(st.nextToken());
                    stack.push(X);
                    break;
                case 2:
                    if (stack.isEmpty()) sb.append(-1);
                    else sb.append(stack.pop());
                    sb.append('\n');
                    break;
                case 3:
                    sb.append(stack.size()).append('\n');
                    break;
                case 4:
                    if (stack.isEmpty()) sb.append(1);
                    else sb.append(0);
                    sb.append('\n');
                    break;
                case 5:
                    if (stack.isEmpty()) sb.append(-1);
                    else sb.append(stack.peek());
                    sb.append('\n');
                    break;
            }
        }
        System.out.println(sb);
    }
}