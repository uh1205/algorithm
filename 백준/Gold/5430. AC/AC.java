import java.io.*;
import java.util.*;

class Main {
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String cmds = br.readLine();
            int n = Integer.parseInt(br.readLine());

            Deque<Integer> dq = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            while (n-- > 0) {
                dq.add(Integer.parseInt(st.nextToken()));
            }

            AC(cmds, dq);
        }

        System.out.println(result);
    }

    static void AC(String cmds, Deque<Integer> dq) {
        boolean reverse = false;
        boolean error = false;

        for (char cmd : cmds.toCharArray()) {
            if (cmd == 'R') {
                reverse = !reverse;
            } else {
                if (dq.isEmpty()) {
                    error = true;
                    break;
                }
                if (reverse) dq.pollLast();
                else dq.pollFirst();
            }
        }

        if (error) {
            result.append("error\n");
        } else {
            result.append("[");
            while (!dq.isEmpty()) {
                result.append(reverse ? dq.pollLast() : dq.pollFirst());
                if (!dq.isEmpty()) result.append(',');
            }
            result.append("]\n");
        }
    }
}