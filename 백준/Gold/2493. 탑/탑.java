import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Tower> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());

            // 현재 탑보다 낮은 탑 모두 제거
            while (!stack.isEmpty() && stack.peek().height < height) {
                stack.pop();
            }
            
            if (stack.isEmpty()) sb.append(0);
            else sb.append(stack.peek().number);
            sb.append(' ');
            
            stack.push(new Tower(i + 1, height));
        }

        System.out.println(sb);
    }

    static class Tower {
        int number, height;

        Tower(int number, int height) {
            this.number = number;
            this.height = height;
        }
    }
}