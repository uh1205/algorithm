import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] result = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Tower> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());

            // 왼쪽에서 나보다 작은 탑은 신호 못 받음 → 제거
            while (!stack.isEmpty() && stack.peek().height <= height) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = stack.peek().index;
            }

            // 현재 탑을 스택에 push (1-based index)
            stack.push(new Tower(height, i + 1));
        }

        StringBuilder sb = new StringBuilder();
        for (int r : result) {
            sb.append(r).append(" ");
        }

        System.out.println(sb);
    }
    
    static class Tower {
        int height;
        int index;

        Tower(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }
}